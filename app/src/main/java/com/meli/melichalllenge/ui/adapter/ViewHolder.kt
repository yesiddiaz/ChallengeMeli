package com.meli.melichalllenge.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.databinding.ItemProductBinding
import com.meli.melichalllenge.ui.model.ProductUI
import com.meli.melichalllenge.util.toCurrency
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(product: ProductUI, onClickItem: (ProductUI) -> Unit) {
        binding.apply {
            root.setOnClickListener {
                onClickItem.invoke(product)
            }
            Picasso.get().load(product.image).into(image)
            tvTitle.text = product.title
            tvPrice.text = product.price
            tvInformation.text = product.condition
            tvLocation.text = product.ubicate
        }
    }
}