package com.beyzanuraydemir.ecommerceapp.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentProductCardBinding
import com.beyzanuraydemir.ecommerceapp.model.CRUDResponse
import com.beyzanuraydemir.ecommerceapp.service.ApiUtils
import com.beyzanuraydemir.ecommerceapp.util.doPlaceHolder
import com.beyzanuraydemir.ecommerceapp.util.downloadImage
import com.beyzanuraydemir.ecommerceapp.viewmodel.ProductCardViewModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ProductCardFragment() : Fragment() {
   private lateinit var binding : FragmentProductCardBinding
   private val viewModel by lazy { ProductCardViewModel(requireContext()) }
   private val args: ProductCardFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.productModel.let {
            binding.productCardTitle.text = it.title
            binding.productCardPrice.text = "$ ${it.price}"
            binding.productCardDescription.text = it.description
            binding.productCardRate.text = it.rate
            binding.productCategory.text = it.category
            binding.productCardImage.downloadImage(it.image, doPlaceHolder(requireContext()))
        }

        binding.buttonAddToCart.setOnClickListener {
            viewModel.addToBag(args.productModel)
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        viewModel.isProductAddedBag.observe(viewLifecycleOwner){

        }
    }


}