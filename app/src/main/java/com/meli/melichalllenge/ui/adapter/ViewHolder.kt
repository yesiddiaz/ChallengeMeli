package com.meli.melichalllenge.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.data.model.ProductModel
import com.meli.melichalllenge.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun bind(product: ProductModel) {
        binding.apply {
            Picasso.get().load(product.body.image).into(image)
            tvTitle.text = product.body.title
            tvPrice.text = product.body.price.toString()
            tvInformation.text = product.body.condition
            tvLocation.text = product.body.ubicate.toString()
        }
    }
}