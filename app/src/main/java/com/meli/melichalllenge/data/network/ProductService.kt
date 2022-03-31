package com.meli.melichalllenge.data.network

import com.meli.melichalllenge.BuildConfig
import com.meli.melichalllenge.core.RetrofitHelper
import com.meli.melichalllenge.data.model.CategoryModel
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.data.model.TopProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCategory(search: String): List<CategoryModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .getPredictCategory(BuildConfig.TokenAPI, search)
            response.body()!!
        }
    }

    suspend fun getTopProducts(category: String): TopProducts? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .getTopProducts(category)
            response.body()
        }
    }

    suspend fun multiGetProducts(ids: String): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductApiClient::class.java)
                .multiGetProducts(BuildConfig.TokenAPI, ids)
            response.body()!!
        }
    }
}