package com.vasist.userlist122.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vasist.userlist122.JsonData.JsonData
import com.vasist.userlist122.network.RetrofitHelper

class QuoteRepository (private val quoteService: RetrofitHelper.QuoteService) {

    private val quotesLiveData = MutableLiveData<JsonData>()
    val quotes: LiveData<JsonData>
        get() = quotesLiveData

    suspend fun getQuotes(){
        val userResults = quoteService.getUserList()
        if ( userResults.body()!=null){
            quotesLiveData.postValue(userResults.body())

        }
    }
}