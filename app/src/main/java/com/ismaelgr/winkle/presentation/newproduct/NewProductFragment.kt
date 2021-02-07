package com.ismaelgr.winkle.presentation.newproduct

import android.text.Editable
import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.util.GlideLoader
import kotlinx.android.synthetic.main.fragment_newproduct.*

/**
 * A simple [Fragment] subclass.
 */
class NewProductFragment : BaseFragment(R.layout.fragment_newproduct), NewProductContract.View {

    private lateinit var newproductPresenter: NewProductContract.Presenter

    override fun loadBigImage(url: String) {
        GlideLoader.load(new_big_image_iv, url)
    }

    override fun loadName(name: String) {
        new_product_name_et.text = Editable.Factory.getInstance().newEditable(name)
    }

    override fun loadDescription(description: String) {
        new_description_et.text = Editable.Factory.getInstance().newEditable(description)
    }

    override fun loadDescriptionImage(url: String) {
        TODO("Not yet implemented")
    }

    override fun loadDisabledOnNextBuy(disableNextBuy: Boolean) {
        new_disable_on_buy_cb.isChecked = disableNextBuy
    }

    override fun enableSaveCreateButton() {
        new_update_create_btn.isEnabled = true
    }

    override fun loadCategories(categorias: List<Categorias>) {
        TODO("Not yet implemented")
    }

    override fun loadCategoria(categoria: Categorias) {
        TODO("Not yet implemented")
    }

    override fun loadEtiquetas(etiquetas: List<String>) {
        TODO("Not yet implemented")
    }

    override fun initElements() {
        newproductPresenter = NewProductPresenter(this as NewProductContract.View)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.newproductPresenter
}
