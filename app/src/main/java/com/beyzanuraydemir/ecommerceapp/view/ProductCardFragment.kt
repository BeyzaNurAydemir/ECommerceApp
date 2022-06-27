package com.beyzanuraydemir.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.adapter.ProductRecyclerAdapter
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentProductCardBinding
import com.beyzanuraydemir.ecommerceapp.model.CRUDResponse
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.service.ApiUtils
import com.beyzanuraydemir.ecommerceapp.service.ProductAPI
import com.beyzanuraydemir.ecommerceapp.service.ProductAPIService
import com.beyzanuraydemir.ecommerceapp.util.doPlaceHolder
import com.beyzanuraydemir.ecommerceapp.util.downloadImage
import com.beyzanuraydemir.ecommerceapp.viewmodel.ProductCardViewModel
import com.beyzanuraydemir.ecommerceapp.viewmodel.ProductListViewModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ProductCardFragment() : Fragment() {
   private lateinit var binding : FragmentProductCardBinding
   private lateinit var viewModel : ProductCardViewModel
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

        viewModel = ViewModelProvider(this).get(ProductCardViewModel::class.java)

        args.productModel.let {
            binding.productCardTitle.text = it.title
            binding.productCardPrice.text = "$ ${it.price}"
            binding.productCardDescription.text = it.description
            binding.productCardRate.text = it.rate
            binding.productCategory.text = it.category
            binding.productCardImage.downloadImage(it.image, doPlaceHolder(requireContext()))
        }

        binding.buttonAddToCart.setOnClickListener {
            addBag()
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    fun addBag(){
        val productDIF = ApiUtils.getProductApiServis()
        args.productModel.let {
            FirebaseAuth.getInstance().currentUser?.let { user->
                productDIF.addToBag(user.uid,it.title,it.price,it.description
                ,it.category,it.image,it.rate,it.count
                ,it.sale_state).enqueue(object : Callback<CRUDResponse>{
                override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                    findNavController().navigate(R.id.action_productCardFragment_to_bagFragment)
                    Log.e("Başarı", response.body()?.success.toString())
                    Log.e("message", response.body()?.message.toString())
                }
                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
            }
        }
    }
}