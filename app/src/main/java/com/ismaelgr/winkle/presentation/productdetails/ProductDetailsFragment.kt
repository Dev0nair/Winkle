package com.ismaelgr.winkle.presentation.productdetails

import android.view.View.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_productdetails.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment(R.layout.fragment_productdetails),
    ProductDetailsContract.View {

    private val productdetailsPresenter: ProductDetailsContract.Presenter by inject<ProductDetailsPresenter> { parametersOf(this) }
    private lateinit var productDetailsRecycler: ProductDetailsRecycler

    override fun setMainImage(url: String) {
        GlideLoader.load(product_detail_image, url)
    }

    override fun setName(name: String) {
        product_detail_name.text = name
    }

    override fun setPrice(price: Float) {
        product_detail_price.text = "${Mapper.map(price)}€"
    }

    override fun setDescription(description: String) {
        product_detail_description.text = description
    }

    override fun setImages(images: List<String>) {
        productDetailsRecycler.setList(images)
    }

    override fun setNumberOnShopList(count: Int) {
//        TODO("Not yet implemented")
    }

    override fun setPuntuation(puntuation: String) {
//        TODO("Not yet implemented")
    }

    override fun setImageProfile(url: String) {
        GlideLoader.load(product_detail_profile_image, url)
    }

    override fun setNameProfile(name: String) {
        product_detail_profile_name.text = name
    }

    override fun setHasFav(hasFav: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun setCountProduct(count: Int) {
        product_detail_count_shoplist.text = getString(R.string.text_count_product_shoplist, count)
    }

    override fun navigateToProfileDetails(idPerfil: String) {
        findNavController().navigate(R.id.action_productDetailsFragment_to_infoProfileFragment, bundleOf("idPerfil" to idPerfil))
    }

    override fun showBigImage(url: String) {
        product_detail_bigimage.run { GlideLoader.load(this, url) }
    }

    override fun showBigImage(show: Boolean){
        if(show){
            animate(
                product_detail_bigimage,
                animId = R.anim.animate_in,
                onStart = {
                    product_detail_back_screen.visibility = VISIBLE
                    product_detail_bigimage.visibility = VISIBLE
                }
            )
        } else {
            animate(
                product_detail_bigimage,
                animId = R.anim.animate_out,
                onFinish = {
                    product_detail_back_screen.visibility = GONE
                    product_detail_bigimage.visibility = INVISIBLE
                }
            )
        }
    }

    override fun writeReasonReport() {
        
    }

    override fun initElements() {
        productDetailsRecycler = ProductDetailsRecycler(productdetailsPresenter::onDetailImageClick)

        product_detail_images_rv.run {
            adapter = productDetailsRecycler
            layoutManager =
                LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL }
        }

        toolbar_title.text = getString(R.string.text_product_info)

        product_detail_profile_viewprofile.setOnClickListener { productdetailsPresenter.onViewProfileClick() }

        product_detail_add_to_shoplist.setOnClickListener { productdetailsPresenter.onAddToShopListClick() }

        product_detail_back_screen.setOnClickListener { productdetailsPresenter.onBackScreenClick() }

        product_detail_report_btn.setOnClickListener{ productdetailsPresenter.onReportClick() }

        productdetailsPresenter.onInit(arguments?.get("producto") as Producto)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.productdetailsPresenter
}
