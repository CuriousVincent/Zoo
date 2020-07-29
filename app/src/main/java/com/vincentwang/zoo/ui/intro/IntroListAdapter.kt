package com.vincentwang.zoo.ui.intro

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincentwang.zoo.R


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class IntroListAdapter(
    private val values: IntroData
) : RecyclerView.Adapter<IntroListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_intro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.results[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.results.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }
}