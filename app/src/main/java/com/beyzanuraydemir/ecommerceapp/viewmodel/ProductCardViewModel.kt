package com.beyzanuraydemir.ecommerceapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.service.ProductDatabase
import kotlinx.coroutines.launch

class ProductCardViewModel(application: Application) :BaseViewModel(application) {

    val productLiveData = MutableLiveData<Product?>()
    val bag = MutableLiveData<List<Product>>()
    val totalBag = MutableLiveData<Int>()

    fun getRoomData(uuid: Int){
        launch {
            val dao = ProductDatabase(getApplication()).productDao()
            val product = dao.getProduct(uuid)
            productLiveData.value = product
        }
    }
}