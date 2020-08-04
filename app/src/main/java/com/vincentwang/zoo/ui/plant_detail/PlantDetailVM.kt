package com.vincentwang.zoo.ui.plant_detail

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.request.RequestListener
import com.vincentwang.zoo.base.BaseVM

class PlantDetailVM : BaseVM() {

    val url = MutableLiveData<String>("")
    val name = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val intro = MutableLiveData<String>()
    val feature = MutableLiveData<String>()
    val function = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    lateinit var listener: RequestListener<Drawable?>

    fun setData(data: PlantDetailFragData,listener: RequestListener<Drawable?>) {
        this.listener = listener
        data.data.apply {
            val sUrl = F_Pic01_URL
                ?: "http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg"
            url.postValue(sUrl)
            name.postValue(F_Name_En)
            nickname.postValue(F_AlsoKnown)
            intro.postValue(F_Brief)
            feature.postValue(F_Feature)
            function.postValue(F_FunctionApplication)
            time.postValue("開放時間 : $F_Update")
        }
    }
    fun getRequestListener(): RequestListener<Drawable?>{
        return listener
    }
}