package com.meli.melichalllenge.ui.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.meli.melichalllenge.R
import com.meli.melichalllenge.databinding.FragmentDetailBinding
import java.lang.Exception
import java.text.NumberFormat

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()
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
            if (args.product.body.id == it) {
                binding.toolbar.menu[0].setIcon(R.drawable.favorite_filled)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.favorite -> {
                    var needAdd = true
                    val set: MutableSet<String> = sharedPref.getStringSet("ids", null) ?: HashSet()
                    val ids = set.toMutableList()
                    try {
                        ids.forEach {
                            if (args.product.body.id == it) {
                                ids.remove(args.product.body.id)
                                needAdd = false
                                return@forEach
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    if (needAdd) ids.add(args.product.body.id)
                    with(sharedPref.edit()) {
                        val newSet: MutableSet<String> = HashSet()
                        newSet.addAll(ids)
                        putStringSet("ids", newSet)
                        commit()
                    }
                    it.setIcon(if (needAdd) R.drawable.favorite_filled else R.drawable.favorite)
                    true
                }
                else -> false
            }
        }
        val list = args.product.body.pictures.map { SlideModel(it.urlImage) }
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 0
        val convert = numberFormat.format(args.product.body.price)
        binding.apply {
            imageSlider.setImageList(list, ScaleTypes.FIT)
            tvTitleDetail.text = args.product.body.title
            tvPriceDetail.text = convert
        }
        return binding.root
    }
}