package com.meli.melichalllenge.data.model

import com.google.gson.annotations.SerializedName

data class TopProducts(
    @SerializedName("content") var products: List<IdsProducts>
)

data class IdsProducts(
    var id: String
)
