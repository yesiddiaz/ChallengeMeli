package com.meli.melichalllenge.data.network

import com.meli.melichalllenge.BuildConfig
import com.meli.melichalllenge.data.model.CategoryModel
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.data.model.ProductsModel
import retrofit2.Response
import retrofit2.http.*

interface ProductApiClient {

    @GET("sites/MLM/domain_discovery/search?limit=1")
    suspend fun predictCategory(
        @Header("Authorization") token: String,
        @Query("q") q: String
    ): Response<List<CategoryModel>>

    @GET("highlights/MLM/category/{category}")
    @Headers("Authorization: Bearer ${BuildConfig.TokenAPI}")
    suspend fun top20Products(
        @Path("category") category: String
    ): Response<ProductsModel>

    @GET("items")
    suspend fun multiGetProducts(
        @Header("Authorization") token: String,
        @Query("ids") ids: String
    ): Response<List<ProductModel>>
}