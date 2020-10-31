package com.ismaelgr.winkle.presentation.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto

class CestaRecyclerAdapter(private val onDeleteClick: (idProducto: String) -> Unit): RecyclerView.Adapter<CestaRecyclerAdapter.CestaViewHolder>() {

    private var productList = arrayListOf<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CestaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item_view, parent, false)
        return CestaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CestaViewHolder, position: Int) {
        val item = this.productList[position]
        holder.run {
            setName(item.nombre)
            setDescription(item.descripcion)
            setImage(item.image)
            setPrice(item.precio)
            setDeleteAction(onDeleteClick)
        }
    }

    override fun getItemCount(): Int  = this.productList.size

    class CestaViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private var id: String = ""
        
        fun setId(id: String) {this.id = id}
        fun setName(name: String) {}
        fun setDescription(desc: String) {}
        fun setImage(url: String) {}
        fun setPrice(price: Float) {}
        fun setDeleteAction(action: (idProducto: String) -> Unit) {}

        // TODO("Not yet implemented")
    }
}