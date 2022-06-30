package com.beyzanuraydemir.ecommerceapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.repos.ProductsRepository
import com.beyzanuraydemir.ecommerceapp.service.ProductDatabase
import kotlinx.coroutines.launch

class ProductCardViewModel(context: Context) : ViewModel() {

    private val productsRepository = ProductsRepository(context)

    private var _isProductAddedBag =MutableLiveData<Boolean>()
    val isProductAddedBag: LiveData<Boolean>
        get() = _isProductAddedBag


    val productLiveData = MutableLiveData<Product?>()
    val bag = MutableLiveData<List<Product>>()
    val totalBag = MutableLiveData<Int>()

    fun addToBag(product: Product){
        productsRepository.addBag(product)
        _isProductAddedBag = productsRepository.isProductAddedBag

    }
}