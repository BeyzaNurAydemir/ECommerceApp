package com.beyzanuraydemir.ecommerceapp.service

class ApiUtils {
    companion object{
        private val BASE_URL = "https://canerture.com/api/ecommerce/"

        fun getProductApiServis(): ProductAPI{
            return ProductAPIService.getClient(BASE_URL).create(ProductAPI::class.java)
        }
    }
}