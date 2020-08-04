package com.vincentwang.zoo.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestListener

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("src")
    fun setImageFromUrl(imageView: ImageView, url: String) {
        imageView.loadFromUrl(url)
    }
    @JvmStatic
    @BindingAdapter("url","listener")
    fun setImageFromUrl(imageView: ImageView, url: String,listener: RequestListener<Drawable?>) {
        imageView.loadFromUrlWithAnim(url,listener)
    }
}