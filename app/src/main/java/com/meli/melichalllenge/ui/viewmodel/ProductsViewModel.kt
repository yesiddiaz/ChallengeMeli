package com.meli.melichalllenge.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.domain.GetProductsUseCase
import kotlinx.coroutines.launch
import com.meli.melichalllenge.data.Result

class ProductsViewModel : ViewModel() {

    private val _productModel = MutableLiveData<Result<List<ProductModel>>>()
    val productModel = _productModel
    var getProductsUseCase = GetProductsUseCase()

    fun search(search: String) {
        viewModelScope.launch {
            val result = getProductsUseCase(search)
            _productModel.postValue(result)
        }
    }
}