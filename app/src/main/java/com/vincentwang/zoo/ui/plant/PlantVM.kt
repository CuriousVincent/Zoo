package com.vincentwang.zoo.ui.plant

import android.widget.ImageView
import androidx.hilt.lifecycle.ViewModelInject
import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.ui.plant_detail.PlantDetailFragData
import com.vincentwang.zoo.util.SingleLiveEvent

class PlantVM @ViewModelInject constructor(): BaseVM() {

    val setTitle = SingleLiveEvent<String>()
    val setAdapter = SingleLiveEvent<List<BaseItemData>>()
    val showWebView = SingleLiveEvent<String>()
    val goPlantDetail = SingleLiveEvent<Pair<ImageView,PlantDetailFragData>>()

    var dataList = ArrayList<BaseItemData>()

    fun getData(data:PlantFragData?){
        data?.apply {
            setTitle.postValue(introData.E_Name)
            dataList.add(PlantAreaItemData(introData))
            plantData.result?.results?.map {
                dataList.add(PlantListItemData(it))
            }
            setAdapter.postValue(dataList)
        }
    }

    fun getShowWeb(position:Int){
        showWebView.postValue((dataList[position] as PlantAreaItemData).data.E_URL)
    }

    fun goDetail(image: ImageView, position:Int){
        val data = PlantDetailFragData((dataList[position] as PlantListItemData).data)
        goPlantDetail.postValue(Pair(image,data))
    }
}