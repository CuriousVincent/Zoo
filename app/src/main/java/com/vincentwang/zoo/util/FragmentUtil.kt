package com.vincentwang.zoo.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import androidx.browser.customtabs.CustomTabsIntent
import com.orhanobut.logger.Logger
import com.vincentwang.zoo.R

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

fun Context.startCustomTabsIntent(url: String) {
    val builder = CustomTabsIntent.Builder()

    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
    val vectorDrawable =
        ContextCompat.getDrawable(this,R.drawable.ic_baseline_arrow_back_24)
    vectorDrawable?.run {
        val bitmap = Bitmap.createBitmap(
            intrinsicWidth,
            intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        builder.setCloseButtonIcon(bitmap)
    }

    try {
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    } catch (e: ActivityNotFoundException) {
        Logger.wtf("No Browser")
    }

}
