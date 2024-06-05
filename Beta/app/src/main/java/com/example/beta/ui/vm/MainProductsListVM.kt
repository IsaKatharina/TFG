package com.example.beta.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainProductsListVM:ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var productsListUiState: String by mutableStateOf("")
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getProducts()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getProducts() {
        productsListUiState = "Set the Mars API status response here!"
    }
}