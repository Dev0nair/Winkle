package com.ismaelgr.winkle.presentation.productdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.util.GlideLoader
import kotlinx.android.synthetic.main.product_detail_image_view.view.*

class ProductDetailsRecycler(private val onImageClick: (String) -> Unit): RecyclerView.Adapter<ProductDetailsRecycler.ProductDetailsHolder>() {

    class ProductDetailsHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun setImage(url: String){
            GlideLoader.load(view.product_detail_description_image, url)
        }
    }

    private var list: ArrayList<String> = ArrayList()

    fun setList(list: List<String>){
        this.list.run {
            addAll(list)
        }
    }

    fun add(url: String){
        list.add(0, url)
    }

    fun remove(url: String){
        list.remove(url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_detail_image_view, parent, false)
        return ProductDetailsHolder(view)
    }

    override fun onBindViewHolder(holder: ProductDetailsHolder, position: Int) {
        val url = list[position]
        holder.run {
            setImage(url)
            itemView.setOnClickListener { onImageClick(url) }
        }
    }

    override fun getItemCount(): Int = list.size
}