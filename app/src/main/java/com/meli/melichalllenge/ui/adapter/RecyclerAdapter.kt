package com.meli.melichalllenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.melichalllenge.R
import com.meli.melichalllenge.data.model.ProductModel

class RecyclerAdapter(private var products: List<ProductModel> = emptyList()) :
    RecyclerView.Adapter<ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(products: List<ProductModel>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}