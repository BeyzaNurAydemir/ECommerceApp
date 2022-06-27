package com.beyzanuraydemir.ecommerceapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.model.ProductBagRoomModel

@Database(entities = arrayOf(Product::class), version = 2)
abstract class ProductDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDAO

    companion object{

        //Singleton

        @Volatile private var instance : ProductDatabase? = null

        private val lock = Any()
        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java, "productdatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

}
