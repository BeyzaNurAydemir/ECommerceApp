package com.beyzanuraydemir.ecommerceapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.repos.ProductsRepository

class BagFragmentViewModel(context: Context) : ViewModel() {

    private val productsRepo = ProductsRepository(context)

    private var _productsBag =MutableLiveData<List<Product>>()
    val productsBag: LiveData<List<Product>>
       get() = _productsBag

    private var _isLoading =MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
     get() = _isLoading

    init {
        getProductsBag()
    }

    private fun getProductsBag(){
        productsRepo.getBagProducts()
        _productsBag = productsRepo.productsBagList
    }

}