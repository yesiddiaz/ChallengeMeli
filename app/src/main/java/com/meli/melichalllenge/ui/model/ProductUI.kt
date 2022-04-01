package com.meli.melichalllenge.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUI(
    var id: String,
    var title: String,
    var price: String,
    var condition: String,
    var image: String,
    var ubicate: String,
    var pictures: List<String>
) : Parcelable


