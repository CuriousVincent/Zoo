package com.vincentwang.zoo.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orhanobut.logger.Logger
import com.vincentwang.zoo.R
import kotlinx.android.synthetic.main.fragment_plant_detail.view.*

fun AppCompatActivity.startFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment).commit()
}

fun Fragment.startFragment(@IdRes containerId: Int, fragment: Fragment, isAddToBackStack: Boolean = true) {
    parentFragmentManager.beginTransaction().apply {
        add(containerId, fragment)
        if (isAddToBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}

fun Fragment.startFragment(@IdRes containerId: Int, current: Fragment, target: Fragment,transactionType: TransactionType = TransactionType.PUSH) {
    parentFragmentManager.beginTransaction().apply {
        transactionType.setCustomAnimations(this)
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
fun ImageView.loadFromUrlWithAnim(url: String,listener: RequestListener<Drawable?>) {
    val circularProgressDrawable = CircularProgressDrawable(this.context.applicationContext)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(this.context.applicationContext)
        .load(url)
        .placeholder(circularProgressDrawable)
        .listener(listener)
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

enum class TransactionType {
    PUSH, PRESENT, NONE;

    fun setCustomAnimations(ft: FragmentTransaction) {
        when (this) {
            PUSH -> ft.setCustomAnimations(
                R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit
            )
            PRESENT -> ft.setCustomAnimations(
                R.anim.fragment_slide_bottom_enter,
                R.anim.fragment_slide_bottom_exit,
                R.anim.fragment_slide_top_enter,
                R.anim.fragment_slide_top_exit
            )
            NONE -> {

            }
        }
    }

}
