package com.ismaelgr.winkle.presentation.productdetails

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.factory.ProductsRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProfileRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.GetProductOwnerUseCase
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_productdetails.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment(R.layout.fragment_productdetails),
    ProductDetailsContract.View {

    private lateinit var productdetailsPresenter: ProductDetailsContract.Presenter
    private lateinit var productDetailsRecycler: ProductDetailsRecycler

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
        productDetailsRecycler.setList(images)
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
        productdetailsPresenter = ProductDetailsPresenter(
            this as ProductDetailsContract.View,
            GetProductOwnerUseCase(ProfileRepositoryFactory().getRepository())
        )

        productDetailsRecycler = ProductDetailsRecycler { url ->
            // TODO("AMPLIACION DE LA IMAGEN DE DESCRIPCION DE UN PRODUCTO")
        }

        product_detail_images_rv.run {
            adapter = productDetailsRecycler
            layoutManager =
                LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL }
        }

        toolbar_title.text = getString(R.string.text_product_info)

        productdetailsPresenter.onInit(arguments?.get("producto") as Producto)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.productdetailsPresenter
}
