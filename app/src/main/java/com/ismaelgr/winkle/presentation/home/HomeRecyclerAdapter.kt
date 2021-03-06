package com.ismaelgr.winkle.presentation.home

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.home_product_item_view.view.*

class HomeRecyclerAdapter(private val onProductClick: (producto: Producto) -> Unit) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    private var listProducts = ArrayList<Producto>()
    private val listProductsCopy = ArrayList<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_product_item_view, parent, false)
        return HomeViewHolder(view)
    }

    private var filterTags = emptyList<String>()
    private var filterCategorias = emptyList<Categorias>()
    private var filterNameDesc = ""

    fun setTags(list: List<String>) {
        this.filterTags = list
    }

    fun setCategories(list: List<Categorias>) {
        this.filterCategorias = list
    }

    fun setNameDesc(string: String) {
        filterNameDesc = string.toLowerCase().trim()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = listProducts[position]

        holder.run {
            setImage(item.mainImage)
            setName(item.nombre)
            setPrice(item.precio)
            this.itemView.setOnClickListener { onProductClick(item) }
        }
    }

    override fun getItemCount(): Int = listProducts.size

    private fun filterCategories() {
        if (filterCategorias.isNotEmpty()) {
            val filtered =
                listProducts.filter { producto -> filterCategorias.contains(Mapper.map(producto.categoria)) }
                    .toTypedArray().clone()
            listProducts.run {
                clear()
                addAll(filtered)
            }
        }
    }

    private fun filterTags() {
        if (filterTags.isNotEmpty()) {
            val filtered = listProducts.filter { producto ->
                producto.etiquetas.any { etiqueta ->
                    filterTags.contains(etiqueta)
                }
            }
            listProducts.run {
                clear()
                addAll(filtered)
            }
        }
    }

    private fun filterNameDesc() {
        if (filterNameDesc.isNotEmpty()) {
            val filtered = listProducts.filter { producto ->
                producto.nombre.toLowerCase().contains(filterNameDesc) ||
                        producto.descripcion.toLowerCase().contains(filterNameDesc)
            }.sortedWith(Comparator { o1, o2 ->
                if (o1.nombre.toLowerCase().contains(filterNameDesc) || o2.nombre.toLowerCase()
                        .contains(filterNameDesc)
                ) {
                    if (o1.nombre.toLowerCase().compareTo(filterNameDesc) > o2.nombre.toLowerCase()
                            .compareTo(filterNameDesc)
                    ) {
                        1
                    } else {
                        0
                    }
                } else {
                    if (o1.descripcion.toLowerCase()
                            .compareTo(filterNameDesc) > o2.descripcion.toLowerCase()
                            .compareTo(filterNameDesc)
                    ) {
                        1
                    } else {
                        0
                    }
                }
            })

            listProducts.run {
                clear()
                addAll(filtered)
            }
        }
    }


    fun setList(list: List<Producto>) {
        setFilters(list)
    }

    fun setFilters(list: List<Producto> = listProductsCopy.clone() as List<Producto>) {
        resetList(list)
        filterCategories()
        filterTags()
        filterNameDesc()
        notifyDataSetChanged()
    }

    private fun resetList(list: List<Producto>) {
        listProductsCopy.run {
            clear()
            addAll(list)
        }

        listProducts.run {
            clear()
            addAll(listProductsCopy)
        }
    }

    class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setImage(url: String) {
            if(url.startsWith("local:")){
                val formatedImage = url.replaceFirst("local:", "")
                BitmapFactory.decodeFile(formatedImage).also { b -> view.product_image.setImageBitmap(b) }
            } else {
                GlideLoader.load(view.product_image, url)
            }
        }

        fun setName(name: String) {
            view.product_name.text = name
        }

        fun setPrice(price: Float) {
            view.product_price.text = view.context.getString(
                R.string.text_price,
                Mapper.map(price)
            )
        }
    }
}