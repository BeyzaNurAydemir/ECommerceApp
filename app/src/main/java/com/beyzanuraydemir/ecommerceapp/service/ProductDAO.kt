package com.beyzanuraydemir.ecommerceapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beyzanuraydemir.ecommerceapp.model.Product

@Dao
interface ProductDAO {
    @Insert
    suspend fun insertAll(vararg product: Product)

    @Query("SELECT * FROM products")
    fun getAllProducts() : List<Product>?

    @Query("SELECT * FROM products WHERE uuid = :productId")
    suspend fun getProduct(productId : Int) : Product?

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}