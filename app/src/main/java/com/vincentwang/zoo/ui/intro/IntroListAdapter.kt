package com.vincentwang.zoo.ui.intro

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.vincentwang.zoo.R
import com.vincentwang.zoo.databinding.ItemIntroBinding
import com.vincentwang.zoo.util.BindingHolder
import com.vincentwang.zoo.util.loadFromUrl


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class IntroListAdapter(
    private val context:Context?,
    private val values: IntroData,
    private val itemClick: ((view:View,position:Int) -> Unit)? = null
) : RecyclerView.Adapter<BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_intro, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = values.results[position]
        holder.binding?.apply {
            when(this){
                is ItemIntroBinding -> {
                    vm = IntroItemVM(item)
//                    context?.apply {
//                        val circularProgressDrawable = CircularProgressDrawable(this)
//                        circularProgressDrawable.strokeWidth = 5f
//                        circularProgressDrawable.centerRadius = 30f
//                        circularProgressDrawable.start()
//                        Glide.with(this)
//                            .load("https://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg")
////                            .load("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300")
//                            .placeholder(circularProgressDrawable)
//                            .into(image)
//                    }
                    itemClick?.invoke(itemContainer, position)
                }
            }
            executePendingBindings()
        }
    }
    override fun getItemCount(): Int = values.results.size
}