package com.vincentwang.zoo.ui.intro

import androidx.lifecycle.MutableLiveData

data class IntroItemVM(
    val results: Result
){
    val title = MutableLiveData<String>(results.E_Name)
    val info = MutableLiveData<String>(results.E_Info)

}