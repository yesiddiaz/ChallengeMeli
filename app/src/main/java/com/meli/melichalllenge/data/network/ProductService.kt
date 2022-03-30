package com.meli.melichalllenge.data.network

import com.meli.melichalllenge.BuildConfig
import com.meli.melichalllenge.core.RetrofitHelper
import com.meli.melichalllenge.data.model.CategoryModel
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.data.model.ProductsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCategory(search: String): List<CategoryModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .predictCategory(BuildConfig.TokenAPI, search)
            response.body() ?: emptyList()
        }
    }

    suspend fun getTop20(category: String): ProductsModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .top20Products(category)
            response.body()
        }
    }

    suspend fun multiGetProducts(ids: String): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .multiGetProducts(BuildConfig.TokenAPI, ids)
            response.body() ?: emptyList()
        }
    }
}