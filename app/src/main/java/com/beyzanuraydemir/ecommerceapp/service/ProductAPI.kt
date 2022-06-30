package com.beyzanuraydemir.ecommerceapp.service

import retrofit2.Call
import com.beyzanuraydemir.ecommerceapp.model.CRUDResponse
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.model.ProductResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductAPI {

    //https://canerture.com/api/ecommerce/get_products.php
    //BASE-URL ->  https://canerture.com/api/ecommerce/

    @GET("get_products.php")
    fun getProducts(): Single<List<Product>>

    @GET("get_products.php")
    fun allProducts(): Call<ProductResponse>

   @POST("add_to_bag.php")
   @FormUrlEncoded
   fun addToBag(
       @Field("user") user: String,
       @Field("title") title: String,
       @Field("price") price: String,
       @Field("description") description: String,
       @Field("category") category: String,
       @Field("image") image: String,
       @Field("rate") rate: String,
       @Field("count") count: String,
       @Field("sale_state") sale_state: String,
   ): Call<CRUDResponse>

   @POST("get_bag_products_by_user.php")
   @FormUrlEncoded
   fun getBagProductsByUser(
       @Field("user") user: String?,
   ): Call<List<Product>>
}