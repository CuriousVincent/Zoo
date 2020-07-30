package com.vincentwang.zoo.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import com.vincentwang.zoo.ui.MainActivity
import java.util.concurrent.TimeUnit

abstract class BaseFragment : Fragment() {
    val AVOID_MULTIPLE_CLICK_MILLISECOND = 600L
    val AVOID_MULTIPLE_CLICK_TIME_TYPE = TimeUnit.MILLISECONDS
    lateinit var act: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = activity as MainActivity
    }
    @SuppressLint("CheckResult")
    fun setOnClickListener(view: View, callback: () -> Unit) {
        RxView.clicks(view)
            .throttleFirst(AVOID_MULTIPLE_CLICK_MILLISECOND, AVOID_MULTIPLE_CLICK_TIME_TYPE)
            .subscribe {
                callback()
            }
    }
}