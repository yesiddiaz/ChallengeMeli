package com.meli.melichalllenge.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.meli.melichalllenge.R
import com.meli.melichalllenge.data.Result
import com.meli.melichalllenge.databinding.FragmentDetailBinding
import com.meli.melichalllenge.ui.viewmodel.DetailViewModel

class DetailFragment : Fragment(), OnMenuItemClickListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModels()

    private val args by navArgs<DetailFragmentArgs>()
    private val product by lazy { args.product }
    private val sharedPref by lazy {
        requireActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val stringSet = sharedPref?.getStringSet("ids", null)
        stringSet?.forEach {
            if (product.id == it) {
                binding.toolbar.menu[0].setIcon(R.drawable.favorite_filled)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.toolbar.setOnMenuItemClickListener(this)
        val list = product.pictures.map { SlideModel(it) }
        detailViewModel.description.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> Unit
                Result.NoData -> Unit
                is Result.Success -> binding.tvDescription.text = it.data
            }
        }
        detailViewModel.getDescription(product.id)
        binding.apply {
            imageSlider.setImageList(list, ScaleTypes.FIT)
            tvTitleDetail.text = product.title
            tvPriceDetail.text = product.price
        }
        return binding.root
    }

    //Detect click in the item favorites
    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        return when (p0?.itemId) {
            R.id.favorite -> {
                var needAdd = true
                val set: MutableSet<String> = sharedPref.getStringSet("ids", null) ?: HashSet()
                val ids = set.toMutableList()
                try {
                    ids.forEach {
                        if (product.id == it) {
                            ids.remove(product.id)
                            needAdd = false
                            return@forEach
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (needAdd) ids.add(product.id)
                with(sharedPref.edit()) {
                    val newSet: MutableSet<String> = HashSet()
                    newSet.addAll(ids)
                    putStringSet("ids", newSet)
                    commit()
                }
                p0.setIcon(if (needAdd) R.drawable.favorite_filled else R.drawable.favorite)
                true
            }
            else -> false
        }
    }
}