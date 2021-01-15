package com.ismaelgr.winkle.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object GlideLoader {

    fun load(imageView: ImageView, url: String){
        Glide.with(imageView)
            .asDrawable()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(url)
            .into(imageView)
    }
}