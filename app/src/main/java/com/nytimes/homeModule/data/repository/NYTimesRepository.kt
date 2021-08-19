package com.nytimes.homeModule.data.repository

import com.nytimes.homeModule.data.model.MostPopularResponse
import com.nytimes.network.NYTimesApi
import retrofit2.Call

class NYTimesRepository {

    fun getMostPopular(period: String): Call<MostPopularResponse> {
        return NYTimesApi.retrofitService.getMostPopular(period)
    }

}