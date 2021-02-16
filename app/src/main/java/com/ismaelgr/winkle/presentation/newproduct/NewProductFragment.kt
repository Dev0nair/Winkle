package com.ismaelgr.winkle.presentation.newproduct

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_newproduct.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


/**
 * A simple [Fragment] subclass.
 */
class NewProductFragment : BaseFragment(R.layout.fragment_newproduct), NewProductContract.View {

    private val newproductPresenter: NewProductContract.Presenter by inject<NewProductPresenter> {
        parametersOf(
            this
        )
    }
    private lateinit var categoriesAdapter: ArrayAdapter<String>
    private lateinit var newProductRecyclerAdapter: NewProductRecyclerAdapter
    private val PICK_MULTIPLE_IMAGE_CODE = 101
    private val PICK_ONE_IMAGE_CODE = 102

    override fun loadBigImage(url: String) {
        if (url.isNotEmpty()) {
            // quitamos el borde
            new_big_image_iv.background = null

            if (url.startsWith("local:")) {
                url.replaceFirst("local:", "")
                    .also { formattedImage ->
                        BitmapFactory.decodeFile(formattedImage)
                            .also { b -> new_big_image_iv.setImageBitmap(b) }
                    }
            } else {
                GlideLoader.load(new_big_image_iv, url)
            }
        }
    }

    override fun loadName(name: String) {
        new_product_name_et.text = Editable.Factory.getInstance().newEditable(name)
    }

    override fun loadDescription(description: String) {
        new_description_et.text = Editable.Factory.getInstance().newEditable(description)
    }

    override fun loadDescriptionImage(vararg url: String) {
        newProductRecyclerAdapter.addImage(*url)
    }

    override fun loadDisabledOnNextBuy(disableNextBuy: Boolean) {
        new_disable_on_buy_cb.isChecked = disableNextBuy
    }

    override fun loadPrice(float: Float) {
        new_price_et.text = Editable.Factory.getInstance().newEditable(float.toString())
    }

    override fun enableSaveCreateButton() {
        new_update_create_btn.isEnabled = true
    }

    override fun loadCategories(categorias: List<Categorias>) {
        categoriesAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Categorias.values().map { Mapper.map(requireContext(), it) }
        )
        new_categorias_sp.adapter = categoriesAdapter
    }

    override fun loadCategoria(categoria: Categorias) {
        val posSelection = categoriesAdapter.getPosition(Mapper.map(requireContext(), categoria))
        new_categorias_sp.setSelection(posSelection)
    }

    override fun loadEtiquetas(etiquetas: List<String>) {
        val etiquetasFormated = TextUtils.join(",", etiquetas)
        new_etiquetas_et.text = Editable.Factory.getInstance().newEditable(etiquetasFormated)
    }

    override fun setChangeListeners() {
        new_product_name_et.doOnTextChanged { text, start, before, count ->
            newproductPresenter.onNewNameInserted(
                new_product_name_et.text.toString()
            )
        }
        new_price_et.doOnTextChanged { text, start, before, count ->
            newproductPresenter.onNewPriceInserted(
                new_price_et.text.toString()
            )
        }
        new_description_et.doOnTextChanged { text, start, before, count ->
            newproductPresenter.onNewDescriptionInserted(
                new_description_et.text.toString()
            )
        }
        new_categorias_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newproductPresenter.onCategoryChanged(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                newproductPresenter.onCategoryChanged(Categorias.OTROS.ordinal)
            }

        }
        new_etiquetas_et.doOnTextChanged { text, start, before, count ->
            newproductPresenter.onNewEtiquetasInserted(
                new_etiquetas_et.text.toString().split(
                    ","
                )
            )
        }
        new_disable_on_buy_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            newproductPresenter.onDisableNextBuyChanged(
                isChecked
            )
        }
        new_enabled_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            newproductPresenter.onEnabledChanged(
                isChecked
            )
        }

        new_big_image_iv.setOnClickListener { newproductPresenter.onAddBigImageClick() }
        new_description_image_iv.setOnClickListener { newproductPresenter.onAddDescriptionImageClick() }

        new_update_create_btn.setOnClickListener { newproductPresenter.onSaveClick() }
    }

    override fun loadEnabled(boolean: Boolean) {
        new_enabled_cb.isChecked = boolean
    }

    override fun chooseImages(multiple: Boolean) {
        val intent = Intent(Intent.ACTION_PICK)
        var multipleImagesInt = PICK_ONE_IMAGE_CODE
        intent.type = "image/*"
        if(multiple){
            intent.putExtra(
                Intent.EXTRA_ALLOW_MULTIPLE, true
            )
            multipleImagesInt = PICK_MULTIPLE_IMAGE_CODE
        }
        startActivityForResult(
            intent,
            multipleImagesInt
        )
    }

    override fun initElements() {
        newProductRecyclerAdapter = NewProductRecyclerAdapter {

        }

        new_description_images_rv.run {
            adapter = newProductRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        var producto: Producto? = null
        arguments?.get("producto")?.let { producto = it as Producto }
        newproductPresenter.onInit(producto)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_MULTIPLE_IMAGE_CODE) {
                data.clipData?.let { clipData ->
                    val imageUris = arrayListOf<Uri>()
                    for (pos in 0 until clipData.itemCount) {
                        imageUris.add(
                            Mapper.getImagePath(
                                clipData.getItemAt(pos).uri,
                                requireContext().contentResolver
                            ).toUri()
                        )
                    }
                    newproductPresenter.onNewLocalImageInserted(imageUris)
                }
            } else if (requestCode == PICK_ONE_IMAGE_CODE) {
                data.data?.let { path ->
                    newproductPresenter.onNewBigImageInserted(
                        Mapper.getImagePath(
                            path,
                            requireContext().contentResolver
                        ).toUri()
                    )
                }
            }
        }
    }

    override fun bindPresenter(): BaseContract.Presenter = this.newproductPresenter
}