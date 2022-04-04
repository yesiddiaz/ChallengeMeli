package com.meli.melichalllenge.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.databinding.ItemProductBinding
import com.meli.melichalllenge.ui.model.ProductUI
import com.squareup.picasso.Picasso

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

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