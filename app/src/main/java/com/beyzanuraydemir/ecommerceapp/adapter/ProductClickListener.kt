package com.beyzanuraydemir.ecommerceapp.adapter

import android.view.View
import com.beyzanuraydemir.ecommerceapp.model.Product

interface ProductClickListener {
    fun productClicked(view: View,product: Product)
}