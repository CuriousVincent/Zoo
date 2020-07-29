package com.vincentwang.zoo.model

import com.vincentwang.zoo.ui.plant.PlantData
import org.json.JSONObject
import retrofit2.http.*

public interface ZooServices {

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    suspend fun getPlantInfo(
        @Query(value="limit") limit: Int,
        @Query(value="offset") offset: Int
    ): PlantData
}