package com.meli.melichalllenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.R
import com.meli.melichalllenge.ui.model.ProductUI

class RecyclerAdapter(
    private var products: List<ProductUI> = emptyList(),
    var onClickItem: (ProductUI) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(products: List<ProductUI>?) {
        if (products != null) {
            this.products = products
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], onClickItem)
    }

    override fun getItemCount(): Int = products.size
}