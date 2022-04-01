package com.meli.melichalllenge.data.model

import com.google.gson.annotations.SerializedName

data class DescriptionModel(
    @SerializedName("plain_text") var description: String
)