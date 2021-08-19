package com.nytimes.homeModule.repository

import com.nytimes.homeModule.model.MostPopularResponse
import com.nytimes.network.NYTimesApi
import retrofit2.Call

class NYTimesRepository {

    fun getMostPopular(period: String): Call<MostPopularResponse> {
        return NYTimesApi.retrofitService.getMostPopular(period)
    }

}