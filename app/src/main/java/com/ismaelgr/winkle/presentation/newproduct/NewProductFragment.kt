package com.ismaelgr.winkle.presentation.newproduct

import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.di.presenterModules
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.presentation.home.HomePresenter
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_newproduct.*
import kotlinx.coroutines.joinAll
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class NewProductFragment : BaseFragment(R.layout.fragment_newproduct), NewProductContract.View {

    private val newproductPresenter: NewProductContract.Presenter by inject<NewProductPresenter> { parametersOf(this) }
    private lateinit var categoriesAdapter: ArrayAdapter<String>

    override fun loadBigImage(url: String) {
        // quitamos el borde
        new_big_image_iv.background = null
        GlideLoader.load(new_big_image_iv, url)
    }

    override fun loadName(name: String) {
        new_product_name_et.text = Editable.Factory.getInstance().newEditable(name)
    }

    override fun loadDescription(description: String) {
        new_description_et.text = Editable.Factory.getInstance().newEditable(description)
    }

    override fun loadDescriptionImage(url: String) {

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
        new_product_name_et.doOnTextChanged { text, start, before, count -> newproductPresenter.onNewNameInserted(new_product_name_et.text.toString()) }
        new_price_et.doOnTextChanged { text, start, before, count -> newproductPresenter.onNewPriceInserted(new_price_et.text.toString()) }
        new_description_et.doOnTextChanged { text, start, before, count -> newproductPresenter.onNewDescriptionInserted(new_description_et.text.toString()) }
        new_categorias_sp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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
        new_etiquetas_et.doOnTextChanged { text, start, before, count -> newproductPresenter.onNewEtiquetasInserted(new_etiquetas_et.text.toString().split(",")) }

        new_update_create_btn.setOnClickListener { newproductPresenter.onSaveClick() }
    }

    override fun initElements() {
        var producto: Producto? = null
        arguments?.get("producto")?.let { producto = it as Producto }
        newproductPresenter.onInit(producto)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.newproductPresenter
}
