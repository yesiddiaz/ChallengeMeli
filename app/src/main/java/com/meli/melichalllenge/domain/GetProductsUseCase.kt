package com.meli.melichalllenge.domain

import com.meli.melichalllenge.data.ProductRepository

class GetProductsUseCase {

    private val repository = ProductRepository()

    suspend operator fun invoke(search: String) = repository.searchProducts(search)
}

class GetProductDescription {

    private val repository = ProductRepository()

    suspend operator fun invoke(id: String) = repository.getDescription(id)
}