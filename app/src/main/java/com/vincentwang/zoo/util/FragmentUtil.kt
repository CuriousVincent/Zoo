package com.vincentwang.zoo.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.startFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment).commit()
}

fun Fragment.startFragment(@IdRes containerId: Int, fragment: Fragment, isAddToBackStack: Boolean = true) {
    fragmentManager?.beginTransaction()?.apply {
        add(containerId, fragment)
        if (isAddToBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}

fun Fragment.startFragment(@IdRes containerId: Int, current: Fragment, target: Fragment) {
    fragmentManager?.beginTransaction()?.apply {
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