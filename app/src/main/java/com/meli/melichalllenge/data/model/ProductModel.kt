package com.meli.melichalllenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    var body: Body
) : Parcelable

@Parcelize
data class Body(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Double,
    @SerializedName("condition") var condition: String,
    @SerializedName("secure_thumbnail") var image: String,
    @SerializedName("seller_address") var ubicate: SellerAddress,
    @SerializedName("pictures") var pictures: List<Pictures>
) : Parcelable

@Parcelize
data class SellerAddress(
    var city: BaseAddress,
    var state: BaseAddress,
    var country: BaseAddress
) : Parcelable {
    override fun toString() = "${city.name}, ${state.name}, ${country.name}"
}

@Parcelize
data class BaseAddress(
    var id: String,
    var name: String
) : Parcelable

@Parcelize
data class Pictures(
    @SerializedName("secure_url") var urlImage: String
) : Parcelable