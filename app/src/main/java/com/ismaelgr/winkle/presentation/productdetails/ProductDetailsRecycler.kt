package com.ismaelgr.winkle.presentation.productdetails

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductDetailsRecycler(private val onImageClick: (String) -> Unit): RecyclerView.Adapter<ProductDetailsRecycler.ProductDetailsHolder>() {

    class ProductDetailsHolder(private val view: View): RecyclerView.ViewHolder(view)

    private var list: ArrayList<String> = ArrayList()

    fun setList(list: List<String>){
        this.list.run {
            clear()
            addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductDetailsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}