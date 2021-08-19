package com.nytimes.homeModule.model

import com.google.gson.annotations.SerializedName

class MostPopularResponse (
    var status: String,
    var copyright: String,
    var num_results:Int,
    @SerializedName("results")
    var mostPopulars: List<MostPopular>? = null
)