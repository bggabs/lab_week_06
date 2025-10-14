package com.example.lab_week_06

import android.widget.ImageView
import android.content.Context
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}
