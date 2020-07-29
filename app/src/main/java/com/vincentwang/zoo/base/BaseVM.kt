package com.vincentwang.zoo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseVM:ViewModel(){
    val isLoading = MutableLiveData<Boolean>()
}