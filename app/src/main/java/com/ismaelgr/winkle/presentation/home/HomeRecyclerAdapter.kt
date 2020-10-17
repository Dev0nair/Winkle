package com.ismaelgr.winkle.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.util.GlideLoader
import kotlinx.android.synthetic.main.home_product_view.view.*

class HomeRecyclerAdapter(private val onProductClick: (idProducto: String) -> Unit) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeAdapter>() {

    private val listProducts = ArrayList<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_product_view, parent, false)
        return HomeAdapter(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter, position: Int) {
        val item = listProducts[position]

        holder.run {
            setImage(item.image)
            setName(item.nombre)
            setPrice(item.precio)
            this.itemView.setOnClickListener { onProductClick(item.id) }
        }
    }

    override fun getItemCount(): Int = listProducts.size


    fun setList(list: List<Producto>) {
        listProducts.run {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class HomeAdapter(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setImage(url: String) {
            GlideLoader.load(view.product_image, url)
        }

        fun setName(name: String) {
            view.product_name.text = name
        }

        fun setPrice(price: Float) {
            view.product_price.text = "$price €"
        }
    }
}