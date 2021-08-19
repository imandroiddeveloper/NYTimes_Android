package com.nytimes.network

object NYTimesApi {
    val retrofitService : NYTimesApiService by lazy {
        NYTimesNetwork.retrofit.create(NYTimesApiService::class.java) }

}