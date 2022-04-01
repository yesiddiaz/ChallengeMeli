package com.meli.melichalllenge.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.databinding.ItemProductBinding
import com.meli.melichalllenge.util.toCurrency
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(product: ProductModel, onClickItem: (ProductModel) -> Unit) {
        binding.apply {
            root.setOnClickListener {
                onClickItem.invoke(product)
            }
            Picasso.get().load(product.body.image).into(image)
            tvTitle.text = product.body.title
            tvPrice.text = product.body.price.toCurrency()
            tvInformation.text = product.body.condition
            tvLocation.text = product.body.ubicate.toString()
        }
    }
}