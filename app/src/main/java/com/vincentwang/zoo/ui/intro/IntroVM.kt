package com.vincentwang.zoo.ui.intro


import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.model.ZooRepository
import com.vincentwang.zoo.ui.plant.PlantData
import com.vincentwang.zoo.util.CoroutineDispatcherProvider
import com.vincentwang.zoo.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class IntroVM(
    private val repo: ZooRepository,
    private val dispatchers: CoroutineDispatcherProvider
) : BaseVM() {

    val setAdapter = SingleLiveEvent<IntroData>()
    val goPlantFragment = SingleLiveEvent<PlantData>()
    val showErrorDialog = SingleLiveEvent<Unit>()

    init {
        showList()
    }

    private fun showList() {
        isLoading.postValue(true)
        viewModelScope.launch {
            repo.getIntro()
                .flowOn(dispatchers.io())
                .catch {
                    isLoading.postValue(false) }
                .collect {
                    setAdapter.postValue(it)
                    isLoading.postValue(false)
                }
        }

    }

    fun getPlant(num: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            repo.getPlant(num)
                .flowOn(dispatchers.io())
                .catch {e->
                    showErrorDialog.postValue(Unit)
                    isLoading.postValue(false)
                }
                .collect{
                    if(it.result != null){
                        goPlantFragment.postValue(it)
                    }else{
                        showErrorDialog.postValue(Unit)
                    }
                    isLoading.postValue(false)
                }
        }
    }
}