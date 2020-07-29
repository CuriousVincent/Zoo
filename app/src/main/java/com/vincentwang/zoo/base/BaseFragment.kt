package com.vincentwang.zoo.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.vincentwang.zoo.ui.MainActivity

abstract class BaseFragment : Fragment() {

    lateinit var act: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = activity as MainActivity
    }

}