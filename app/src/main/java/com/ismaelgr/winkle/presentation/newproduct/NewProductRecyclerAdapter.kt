package com.ismaelgr.winkle.presentation.newproduct

import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.util.GlideLoader
import com.ismaelgr.winkle.util.Mapper
import kotlinx.android.synthetic.main.fragment_newproduct.*

class NewProductRecyclerAdapter(private val onImageClick: (urlImage: String) -> Unit):
    RecyclerView.Adapter<NewProductRecyclerAdapter.NewProductRecyclerVH>() {

    private var imageList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductRecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_product_description_image_view, parent, false)
        return NewProductRecyclerVH(view)
    }

    override fun onBindViewHolder(holder: NewProductRecyclerVH, position: Int) {
        val urlImage = imageList[position]
        holder.itemView.setOnClickListener { onImageClick(urlImage) }

        val imageView = (holder.itemView as ImageView).apply { setImageURI(null) }

        if(urlImage.startsWith("local:")){
            urlImage.replaceFirst("local:", "").also{ formattedImage ->
                imageView.setImageURI(formattedImage.toUri())
            }
        } else {
            GlideLoader.load(imageView, urlImage)
        }
    }

    fun addImage(vararg url: String) {
        imageList.run {
            clear()
            addAll(0, url.toList())
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = imageList.size

    class NewProductRecyclerVH(view: View): RecyclerView.ViewHolder(view)
}