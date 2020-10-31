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
    private val onItemClick: (idProducto: String) -> Unit
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
            setId(item.id)
            setName(item.nombre)
            setDescription(item.descripcion)
            setImage(item.image)
            setPrice(item.precio)
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
        private var id: String = ""

        fun setId(id: String) {
            this.id = id
        }

        fun setName(name: String) {
            view.shoppinglistitem_title_tv.text = name
        }

        fun setDescription(desc: String) {
            view.shoppinglistitem_desc_tv.text = desc
        }

        fun setImage(url: String) {
            GlideLoader.load(view.shoppinglistitem_civ, url)
        }

        fun setPrice(price: Float) {
            view.shoppinglistitem_price_tv.text = view.context.getString(
                R.string.text_price,
                Mapper.map(price)
            )
        }

        fun setDeleteAction(action: (idProducto: String) -> Unit) {
            view.shoppinglistitem_quitar_btn.setOnClickListener { action(id) }
        }

        fun setClickAction(onItemClick: (idProducto: String) -> Unit) {
            view.setOnClickListener { onItemClick(id) }
        }
    }
}