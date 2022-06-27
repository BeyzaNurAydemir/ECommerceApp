package com.beyzanuraydemir.ecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {
    private lateinit var binding : FragmentFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        return binding.root
    }

}