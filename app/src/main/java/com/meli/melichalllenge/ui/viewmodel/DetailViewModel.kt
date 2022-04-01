package com.meli.melichalllenge.ui.viewmodel

import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichalllenge.data.Result
import com.meli.melichalllenge.data.model.DescriptionModel
import com.meli.melichalllenge.domain.GetProductDescription
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _productModel = MutableLiveData<Result<DescriptionModel>>()
    val productModel = _productModel
    var getDescriptionUseCase = GetProductDescription()

    fun getDescription(id: String) {
        viewModelScope.launch {
            val result = getDescriptionUseCase.invoke(id)
            _productModel.postValue(result)
        }
    }
}