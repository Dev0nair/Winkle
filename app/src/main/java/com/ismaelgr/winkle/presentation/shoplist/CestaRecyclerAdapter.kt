package com.ismaelgr.winkle.presentation.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.shopping_list_item_view.view.*

class CestaRecyclerAdapter(
    private val onDeleteClick: (idProducto: String) -> Unit,
    private val onItemClick: (producto: Producto) -> Unit
) :
    RecyclerView.Adapter<CestaRecyclerAdapter.CestaViewHolder>() {

    private var productList = arrayListOf<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CestaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_item_view, parent, false)
        return CestaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CestaViewHolder, position: Int) {
        val item = this.productList[position]
        holder.run {
            setProduct(item)
            updateImage()
            updateName()
            updatePrice()
            updateDescription()
            setDeleteAction(onDeleteClick)
            setClickAction(onItemClick)
        }
    }

    override fun getItemCount(): Int = this.productList.size

    fun loadList(productos: List<Producto>) {
        this.productList.run {
            clear()
            addAll(productos)
        }
        notifyDataSetChanged()
    }

    class CestaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var producto: Producto

        fun setProduct(producto: Producto) {
            this.producto = producto
        }

        fun updateName() {
            view.shoppinglistitem_title_tv.text = this.producto.nombre
        }

        fun updateDescription() {
            view.shoppinglistitem_desc_tv.text = this.producto.descripcion
        }

        fun updateImage() {
            GlideLoader.load(view.shoppinglistitem_civ, this.producto.mainImage)
        }

        fun updatePrice() {
            view.shoppinglistitem_price_tv.text = view.context.getString(
                R.string.text_price,
                Mapper.map(this.producto.precio)
            )
        }

        fun setDeleteAction(action: (idProducto: String) -> Unit) {
            view.shoppinglistitem_quitar_btn.setOnClickListener { action(this.producto.id) }
        }

        fun setClickAction(onItemClick: (Producto) -> Unit) {
            view.setOnClickListener { onItemClick(this.producto) }
        }
    }
}