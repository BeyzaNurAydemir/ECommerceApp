package com.beyzanuraydemir.ecommerceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productsbagdatabase")
data class ProductBagRoomModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "rate")
    val rate: String,
    @ColumnInfo(name = "count")
    val count: String,
    @ColumnInfo(name = "sale_state")
    val sale_state: String
) {
}