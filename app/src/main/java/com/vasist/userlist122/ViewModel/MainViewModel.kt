package com.vasist.userlist122.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.vasist.userlist122.JsonData.JsonData
import com.vasist.userlist122.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: QuoteRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getQuotes()
        }
    }
    val quotes : LiveData<JsonData>
        get() = repository.quotes
}