package com.meli.melichalllenge.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichalllenge.data.Result
import com.meli.melichalllenge.domain.GetProductsUseCase
import com.meli.melichalllenge.ui.model.ProductUI
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _productModel = MutableLiveData<Result<List<ProductUI>>>()
    val productModel = _productModel
    var getProductsUseCase = GetProductsUseCase()

    fun searchProducts(search: String) {
        viewModelScope.launch {
            val result = getProductsUseCase(search)
            _productModel.postValue(result)
        }
    }
}