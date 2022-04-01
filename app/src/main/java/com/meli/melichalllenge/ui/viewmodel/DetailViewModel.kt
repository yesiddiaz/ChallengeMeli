package com.meli.melichalllenge.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichalllenge.data.Result
import com.meli.melichalllenge.domain.GetProductDescription
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _description = MutableLiveData<Result<String>>()
    val description = _description
    var getDescriptionUseCase = GetProductDescription()

    fun getDescription(id: String) {
        viewModelScope.launch {
            val result = getDescriptionUseCase.invoke(id)
            _description.postValue(result)
        }
    }
}