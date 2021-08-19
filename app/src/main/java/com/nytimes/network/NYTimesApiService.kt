package com.nytimes.network

import com.nytimes.BuildConfig.AUTHKEY
import com.nytimes.homeModule.model.MostPopularResponse
import retrofit2.Call
import retrofit2.http.*

interface NYTimesApiService {

    @GET("mostpopular/v2/emailed/{period}.json?api-key=$AUTHKEY")
    fun getMostPopular(@Path("period") period:String): Call<MostPopularResponse>


}