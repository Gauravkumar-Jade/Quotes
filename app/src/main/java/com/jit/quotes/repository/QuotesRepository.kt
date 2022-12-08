package com.jit.quotes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jit.quotes.api.QuotesApi
import com.jit.quotes.data.Quotes

class QuotesRepository() {

    private var _quoteList = MutableLiveData<Quotes>()
    val quoteList:LiveData<Quotes> get() = _quoteList


    suspend fun getQuotes(){
        val result = QuotesApi.retrofitService.getQuotes()

        _quoteList.postValue(result)
    }
}