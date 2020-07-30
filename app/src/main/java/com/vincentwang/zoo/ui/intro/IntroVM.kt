package com.vincentwang.zoo.ui.intro


import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.vincentwang.zoo.base.BaseVM
import com.vincentwang.zoo.model.ZooRepository
import com.vincentwang.zoo.ui.plant.PlantData
import com.vincentwang.zoo.ui.plant.PlantFragData
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
    val goPlantFragment = SingleLiveEvent<PlantFragData>()
    val showErrorDialog = SingleLiveEvent<Unit>()
    lateinit var data: IntroData
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
                    data = it
                    setAdapter.postValue(data)
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
                        goPlantFragment.postValue(PlantFragData(it, data.results[num]))
                    }else{
                        showErrorDialog.postValue(Unit)
                    }
                    isLoading.postValue(false)
                }
        }
    }
}