package com.beyzanuraydemir.ecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentBagBinding
import com.beyzanuraydemir.ecommerceapp.model.Product


class BagFragment : Fragment() {
    private lateinit var binding : FragmentBagBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bag, container, false)
        return binding.root
    }


}