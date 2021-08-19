package com.nytimes.homeModule.model

import com.google.gson.annotations.SerializedName

class Medium(
    var type: String,
    var subtype: String,
    var caption: String,
    var copyright: String,
    var approved_for_syndication: Int,
    @SerializedName("media-metadata")
    var mediaMetadata: List<MediaMetadata>
)