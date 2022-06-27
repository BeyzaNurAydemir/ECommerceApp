package com.beyzanuraydemir.ecommerceapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanuraydemir.ecommerceapp.model.ProductBagRoomModel

class BagFragmentViewModel(context: Context) : ViewModel() {

    //private val ProductsRepo = ProductsRepository(context)

    private var _productBag =MutableLiveData<List<ProductBagRoomModel>>()
    val productBag: LiveData<List<ProductBagRoomModel>>
       get() = _productBag

    init {
        getProductsBag()
    }

    private fun getProductsBag(){
       /* productsRepo.productsBag()
        _productBag = productsRepo.productsBagList */
    }
}