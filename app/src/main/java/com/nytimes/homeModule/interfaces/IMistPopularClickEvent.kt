package com.nytimes.homeModule.interfaces

import com.nytimes.homeModule.data.model.MostPopular

interface IMostPopularClickEvent {

    fun onClickEvent(mostPopular: MostPopular)
}