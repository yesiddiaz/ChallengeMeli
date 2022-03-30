package com.meli.melichalllenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductsModel(
    @SerializedName("content") var products: List<Content>
)

data class Content(
    var id: String
)
