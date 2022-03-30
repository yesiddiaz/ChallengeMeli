package com.meli.melichalllenge.data

import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.data.network.ProductService

class ProductRepository {
    private val api = ProductService()

    suspend fun search(search: String): List<ProductModel> {
        val category = api.getCategory(search)
        val top20 = api.getTop20(category[0].category) ?: return emptyList()
        var ids = ""
        top20.products.forEach {
            ids += "${it.id},"
        }
        return api.multiGetProducts(ids)
    }
}