package com.vincentwang.zoo.ui.plant

import com.vincentwang.zoo.ui.intro.Result

data class PlantListItemVM(val data : ResultX){
    val title:String = data.F_Name_En
    val info:String = data.F_AlsoKnown
}

data class PlantAreaItemVM(val data:Result){
    val info = data.E_Info
    val time = data.E_Memo
    val category = data.E_Category
    val url = data.E_Pic_URL
}


data class PlantListItemData(val data : ResultX, override val itemType: Int = PlantItemType.List.ordinal):BaseItemData

data class PlantAreaItemData(val data: Result, override val itemType: Int = PlantItemType.Area.ordinal):BaseItemData

interface BaseItemData {
    val itemType: Int
}

enum class PlantItemType {
    Area,List
}