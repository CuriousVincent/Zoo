package com.vincentwang.zoo.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("src")
    fun setImageFromUrl(imageView: ImageView, url: String) {
        imageView.loadFromUrl(url)
    }
}