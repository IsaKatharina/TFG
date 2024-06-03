package com.example.tfg.feature_list.presentation.products

import androidx.lifecycle.ViewModel
import com.example.tfg.feature_list.domain.use_case.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val productsUseCases: ProductsUseCases
):ViewModel(){



}