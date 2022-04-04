package com.meli.melichalllenge.ui.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.InputFilter
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.meli.melichalllenge.R
import com.meli.melichalllenge.data.Result
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
        //Add filter not accept special characters into search edittext
        val filter =
            InputFilter { source, start, end, _, _, _ ->
                for (i in start until end) {
                    if (!Character.isLetterOrDigit(source[i])) {
                        return@InputFilter ""
                    }
                }
                null
            }
        binding.etSearch.filters = arrayOf(filter)

        productsViewModel.productModel.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> {
                    adapter.updateList(emptyList())
                    binding.tvError.text = getString(R.string.error)
                }
                is Result.Success -> {
                    adapter.updateList(result.data)
                    binding.tvError.text = ""
                }
                Result.NoData -> {
                    adapter.updateList(emptyList())
                    binding.tvError.text = getString(R.string.nos_found_products)
                }
            }
        }

        //Capture the enter to perform the respective search
        binding.etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event != null) {
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (binding.etSearch.text.isNullOrEmpty()) {
                            Snackbar.make(
                                binding.root,
                                "Please enter a character",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            binding.tvError.text = ""
                        } else {
                            productsViewModel.searchProducts(binding.etSearch.text.toString())
                            hideSoftKeyboard()
                        }
                        return true
                    }
                }
                return false
            }
        })
        return binding.root
    }

    //Init recycler view
    private fun initRecyclerView() {
        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment)
        adapter = RecyclerAdapter {
            navController.navigate(
                ProductsFragmentDirections.goDetailFragment(
                    it
                )
            )
        }
        binding.rvProducts.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvProducts.adapter = adapter
    }

    //Hide keyBoard
    private fun hideSoftKeyboard() {
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}