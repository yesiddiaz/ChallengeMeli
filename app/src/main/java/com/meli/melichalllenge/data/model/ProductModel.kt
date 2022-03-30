package com.meli.melichalllenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    var body: Body
)

data class Body(
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Double,
    @SerializedName("condition") var condition: String,
    @SerializedName("secure_thumbnail") var image: String,
    @SerializedName("seller_address") var ubicate: SellerAddress
)

data class SellerAddress(
    var city: Base,
    var state: Base,
    var country: Base
) {
    override fun toString() = "${city.name}, ${state.name}, ${country.name}"
}

data class Base(
    var id: String,
    var name: String
)