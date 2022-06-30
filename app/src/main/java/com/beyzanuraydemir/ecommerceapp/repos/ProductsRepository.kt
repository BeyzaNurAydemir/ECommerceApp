package com.beyzanuraydemir.ecommerceapp.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beyzanuraydemir.ecommerceapp.model.CRUDResponse
import com.beyzanuraydemir.ecommerceapp.service.ApiUtils
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.model.ProductResponse
import com.beyzanuraydemir.ecommerceapp.service.*
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository(context: Context) {

    var productsList = MutableLiveData<List<Product>>()

    var productsBagList = MutableLiveData<List<Product>>()

    var isLoading = MutableLiveData<Boolean>()

    var isProductAddedBag = MutableLiveData<Boolean>()


    private val productAPI : ProductAPI = ApiUtils.getProductApiServis()

    private val productDAO: ProductDAO? = ProductDatabase(context).productDao()

    fun products(){
        isLoading.value = true
        productAPI.allProducts().enqueue(object : Callback<ProductResponse>{
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>){
                response.body()?.products.let {
                    productsList.value = it
                    isLoading.value = false
                } ?: run{
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable){
                t.localizedMessage?.toString()?.let { Log.e("Products Failure", it) }
                isLoading.value = false
            }
        })
    }


    fun getBagProducts(){
        val bagDIF = ApiUtils.getProductApiServis()
        FirebaseAuth.getInstance().currentUser?.uid.let { user ->
            bagDIF.getBagProductsByUser(user).enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    response.body()?.let {
                        productsBagList.value = it
                    }


                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }

    fun addBag(product: Product){
        val productDIF = ApiUtils.getProductApiServis()
        product.let {
            FirebaseAuth.getInstance().currentUser?.let { user->
                productDIF.addToBag(user.uid,it.title,it.price,it.description
                    ,it.category,it.image,it.rate,it.count
                    ,it.sale_state).enqueue(object : Callback<CRUDResponse>{
                    override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                        response.body()?.let {
                            isProductAddedBag.value = it.success == 1
                        }

                        Log.e("Başarı", response.body()?.success.toString())
                        Log.e("message", response.body()?.message.toString())
                    }
                    override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                        isProductAddedBag.value = false
                        Log.e("failure", t.message.orEmpty())
                    }
                })
            }
        }
    }

}