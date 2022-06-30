package com.beyzanuraydemir.ecommerceapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    @Expose
    var products: List<Product>,
    @SerializedName("success")
    @Expose
    var success: Int
) {
}