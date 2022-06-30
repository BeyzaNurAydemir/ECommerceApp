package com.beyzanuraydemir.ecommerceapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity (tableName = "products")
data class Product(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,
    @ColumnInfo(name = "user")
    @SerializedName("user")
    val user: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,
    @ColumnInfo(name = "category")
    @SerializedName("category")
    val category: String,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,
    @ColumnInfo(name = "rate")
    @SerializedName("rate")
    val rate: String,
    @ColumnInfo(name = "count")
    @SerializedName("count")
    val count: String,
    @ColumnInfo(name = "sale_state")
    @SerializedName("sale_state")
    val sale_state: String
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}