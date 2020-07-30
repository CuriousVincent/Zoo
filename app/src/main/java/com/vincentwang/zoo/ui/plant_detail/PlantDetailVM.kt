package com.vincentwang.zoo.ui.plant_detail

import androidx.lifecycle.MutableLiveData
import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.ui.plant.ResultX

class PlantDetailVM : BaseVM() {

    val url = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val intro = MutableLiveData<String>()
    val feature = MutableLiveData<String>()
    val function = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun setData(data: PlantDetailFragData) {
        data.data.apply {
            url.postValue(F_Pic01_URL)
            name.postValue(F_Name_En)
            nickname.postValue(F_AlsoKnown)
            intro.postValue(F_Brief)
            feature.postValue(F_Feature)
            function.postValue(F_FunctionApplication)
            time.postValue("開放時間 : $F_FunctionApplication")
        }

    }
}