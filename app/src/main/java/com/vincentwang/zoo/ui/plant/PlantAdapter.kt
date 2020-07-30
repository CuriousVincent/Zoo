package com.vincentwang.zoo.ui.plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vincentwang.zoo.R
import com.vincentwang.zoo.databinding.ItemPlantAreaBinding
import com.vincentwang.zoo.databinding.ItemPlantListBinding
import com.vincentwang.zoo.databinding.ItemPlantListBindingImpl
import com.vincentwang.zoo.ui.intro.IntroData
import com.vincentwang.zoo.util.BindingHolder

class PlantAdapter(
    private val data: List<BaseItemData>,
    private val itemClick: ((view: View, position:Int) -> Unit)? = null
) : RecyclerView.Adapter<BindingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        return when (viewType) {
            PlantItemType.List.ordinal->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_plant_list, parent, false)
                BindingHolder(view)
            }
            PlantItemType.Area.ordinal-> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_plant_area, parent, false)
                BindingHolder(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_plant_list, parent, false)
                BindingHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.apply {
            when (this) {
                is ItemPlantAreaBinding->{
                    vm = PlantAreaItemVM((data[position] as PlantAreaItemData).data)
                    itemClick?.invoke(web,position)
                }
                is ItemPlantListBinding ->{
                    vm = PlantListItemVM((data[position] as PlantListItemData).data)
                    itemClick?.invoke(itemContainer,position)
                }
            }
            executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].itemType
    }
}