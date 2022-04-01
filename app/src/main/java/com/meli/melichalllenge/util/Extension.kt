package com.meli.melichalllenge.util

import java.text.NumberFormat

fun Number.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(this)
}