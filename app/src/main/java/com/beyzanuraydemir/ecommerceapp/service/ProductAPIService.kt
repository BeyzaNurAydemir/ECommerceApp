package com.beyzanuraydemir.ecommerceapp.service

import com.beyzanuraydemir.ecommerceapp.model.Product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIService {

    companion object {
        //https://canerture.com/api/ecommerce/get_products.php
        //BASE-URL ->  https://canerture.com/api/ecommerce/

        private val BASE_URL = "https://canerture.com/api/ecommerce/"
        private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ProductAPI::class.java)



        fun getClient(baseUrl: String): Retrofit{
            return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }



    }
    fun getData(): Single<List<Product>> {
        return api.getProducts()
    }
}