package com.vincentwang.zoo.ui.plant

import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.ui.plant_detail.PlantDetailFragData
import com.vincentwang.zoo.util.SingleLiveEvent

class PlantVM: BaseVM() {

    val setTitle = SingleLiveEvent<String>()
    val setAdapter = SingleLiveEvent<List<BaseItemData>>()
    val showWebView = SingleLiveEvent<String>()
    val goPlantDetail = SingleLiveEvent<PlantDetailFragData>()

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

    fun goDetail(position:Int){
        val data = PlantDetailFragData((dataList[position] as PlantListItemData).data)
        goPlantDetail.postValue(data)
    }
}