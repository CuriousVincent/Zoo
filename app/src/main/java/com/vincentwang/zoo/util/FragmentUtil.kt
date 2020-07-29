package com.vincentwang.zoo.util

import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun AppCompatActivity.startFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment).commit()
}

fun Fragment.startFragment(@IdRes containerId: Int, fragment: Fragment, isAddToBackStack: Boolean = true) {
    parentFragmentManager.beginTransaction()?.apply {
        add(containerId, fragment)
        if (isAddToBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}

fun Fragment.startFragment(@IdRes containerId: Int, current: Fragment, target: Fragment) {
    parentFragmentManager.beginTransaction()?.apply {
        if (!target.isAdded) {
            hide(current)
            add(containerId, target)
            addToBackStack(null)
            commit()
        } else {
            hide(current)
            show(target)
            addToBackStack(null)
            commit()
        }
    }
}


fun ImageView.loadFromUrl(url: String) {
    val circularProgressDrawable = CircularProgressDrawable(this.context.applicationContext)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(this.context.applicationContext)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(this)
}
