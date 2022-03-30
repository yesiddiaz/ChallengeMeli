package com.meli.melichalllenge.domain

import com.meli.melichalllenge.data.ProductRepository

class GetProductsUseCase {

    private val repository = ProductRepository()

    suspend operator fun invoke(search: String) = repository.search(search)
}