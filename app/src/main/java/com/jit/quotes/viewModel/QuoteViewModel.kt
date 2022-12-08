package com.jit.quotes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jit.quotes.data.Quotes
import com.jit.quotes.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuotesRepository):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes()
        }
    }

    val quotes: LiveData<Quotes> get() = repository.quoteList

}

class QuoteViewModelFactory(private val repository: QuotesRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(QuoteViewModel::class.java)){
            return QuoteViewModel(repository) as T
        }
       throw IllegalArgumentException("Not Model Class")
    }
}