package com.nytimes.homeModule.viewModel

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.nytimes.homeModule.model.MostPopularResponse
import com.nytimes.homeModule.repository.NYTimesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYTimesViewModel : ViewModel() {
    val mutableLiveData= MutableLiveData<Any>()
    val ynTimesRepository = NYTimesRepository()

    fun getMostPopular(period: String) {
        ynTimesRepository.getMostPopular(period).enqueue(object :Callback<MostPopularResponse>{
           override fun onResponse(call: Call<MostPopularResponse>, response: Response<MostPopularResponse>) {
             if(response.isSuccessful)
                 mutableLiveData.postValue(response.body())
           }
           override fun onFailure(call: Call<MostPopularResponse>, t: Throwable) {
              mutableLiveData.postValue(t.localizedMessage)
           }

       })
    }



}