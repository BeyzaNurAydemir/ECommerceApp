package com.beyzanuraydemir.ecommerceapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beyzanuraydemir.ecommerceapp.model.Product

@Dao
interface ProductDAO {
    @Insert
    suspend fun insertAll(vararg product: Product)

    @Query("SELECT * FROM product")
    suspend fun getAllProducts() : List<Product>?

    @Query("SELECT * FROM product WHERE uuid = :productId")
    suspend fun getProduct(productId : Int) : Product?

    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()
}