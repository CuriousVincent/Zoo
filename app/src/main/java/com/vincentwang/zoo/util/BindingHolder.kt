package com.vincentwang.zoo.util

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
}