package com.nytimes.homeModule.interfaces

import com.nytimes.homeModule.model.MostPopular

interface IMostPopularClickEvent {

    fun onClickEvent(mostPopular: MostPopular)
}