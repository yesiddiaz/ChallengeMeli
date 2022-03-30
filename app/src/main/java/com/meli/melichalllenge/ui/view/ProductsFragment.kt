package com.meli.melichalllenge.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.meli.melichalllenge.databinding.FragmentProductsBinding
import com.meli.melichalllenge.ui.adapter.RecyclerAdapter
import com.meli.melichalllenge.ui.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerAdapter
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        initRecyclerView()
        productsViewModel.productModel.observe(viewLifecycleOwner) { list ->
            Toast.makeText(requireContext(), list.size.toString(), Toast.LENGTH_SHORT).show()
            adapter.updateList(list)
        }
        binding.btnnnn.setOnClickListener {
            productsViewModel.search(binding.etSearch.text.toString())
        }
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = RecyclerAdapter()
        binding.rvProducts.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvProducts.adapter = adapter
    }
}