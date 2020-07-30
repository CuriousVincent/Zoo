package com.vincentwang.zoo.ui.plant

import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.util.SingleLiveEvent

class PlantVM: BaseVM() {

    val setTitle = SingleLiveEvent<String>()

    fun getData(data:PlantFragData?){
        data?.apply {
            setTitle.postValue(introData.E_Name)
        }
    }
}