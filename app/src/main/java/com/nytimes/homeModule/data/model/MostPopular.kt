package com.nytimes.homeModule.data.model

import com.google.gson.annotations.SerializedName

class MostPopular (
    var uri:String,
    var url:String,
    var id:Long,
    var asset_id:Long,
    var source:String,
    @SerializedName("published_date")
    var publishedDate:String,
    var updated:String,
    var section:String,
    var subsection:String,
    var nytdsection:String,
    var adx_keywords:String,
    var byline:String,
    var type:String,
    var title:String,
    @SerializedName("abstract")
    var abstractValue:String,
    var media:List<Medium>,
    var eta_id:Int,
    var des_facet:List<String>,
    var org_facet:List<String>,
    var per_facet:List<Object>,
    var geo_facet:List<Object>)