package com.ismaelgr.winkle.presentation.productdetails

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_productdetails.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment(R.layout.fragment_productdetails), ProductDetailsContract.View {

    private lateinit var productdetailsPresenter: ProductDetailsContract.Presenter

    override fun setMainImage(url: String) {
        GlideLoader.load(product_detail_image, url)
    }

    override fun setName(name: String) {
        product_detail_name.text = name
    }

    override fun setPrice(price: Float) {
        product_detail_price.text = Mapper.map(price)
    }

    override fun setDescription(description: String) {
        product_detail_description.text = description
    }

    override fun setImages(images: List<String>) {

    }

    override fun setNumberOnShopList(count: Int) {
        TODO("Not yet implemented")
    }

    override fun setPuntuation(puntuation: String) {
        TODO("Not yet implemented")
    }

    override fun setImageProfile(url: String) {
        TODO("Not yet implemented")
    }

    override fun setNameProfile(name: String) {
        TODO("Not yet implemented")
    }

    override fun setHasFav(hasFav: Boolean) {
        TODO("Not yet implemented")
    }

    override fun initElements() {
        productdetailsPresenter = ProductDetailsPresenter(this as ProductDetailsContract.View)

        productdetailsPresenter.onInit(arguments?.get("producto") as Producto)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.productdetailsPresenter
}
