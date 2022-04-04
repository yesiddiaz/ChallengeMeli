package com.meli.melichalllenge.data.network

import com.meli.melichalllenge.BuildConfig
import com.meli.melichalllenge.data.model.CategoryModel
import com.meli.melichalllenge.data.model.DescriptionModel
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.data.model.TopProducts
import retrofit2.Response
import retrofit2.http.*

interface ProductApiClient {

    //Obtain predict category sending a text string
    @GET("sites/MLM/domain_discovery/search?limit=1")
    suspend fun getPredictCategory(
        @Header("Authorization") token: String,
        @Query("q") q: String
    ): Response<List<CategoryModel>>

    //Obtain top 20 product of a category
    @GET("highlights/MLM/category/{category}")
    @Headers("Authorization: Bearer ${BuildConfig.TokenAPI}")
    suspend fun getTopProducts(
        @Path("category") category: String
    ): Response<TopProducts>

    //Obtain characteristics of a lot products sending ids
    @GET("items")
    suspend fun multiGetProducts(
        @Header("Authorization") token: String,
        @Query("ids") ids: String
    ): Response<List<ProductModel>>

    //Obtain item description of a product
    @Headers("Authorization: Bearer ${BuildConfig.TokenAPI}")
    @GET("items/{idItem}/description")
    suspend fun getItemDescription(@Path("idItem") id:String) :Response<DescriptionModel>
}