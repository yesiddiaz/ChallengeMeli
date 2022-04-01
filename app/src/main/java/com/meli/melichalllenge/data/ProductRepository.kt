package com.meli.melichalllenge.data

import com.meli.melichalllenge.data.model.DescriptionModel
import com.meli.melichalllenge.data.model.toUI
import com.meli.melichalllenge.data.network.ProductService
import com.meli.melichalllenge.ui.model.ProductUI

class ProductRepository {
    private val api = ProductService()

    suspend fun searchProducts(search: String): Result<List<ProductUI>> {
        try {
            val category = api.getCategory(search)
            if (category.isEmpty()) return Result.NoData
            val top20 = api.getTopProducts(category[0].category)!!
            var ids = ""
            top20.products.forEach {
                ids += "${it.id},"
            }
            if (ids.isEmpty()) return Result.NoData
            return Result.Success(api.multiGetProducts(ids).toUI())
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun getDescription(id: String): Result<String> {
        try {
            val description = api.getDescription(id) ?: return Result.NoData
            return Result.Success(description.description)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}