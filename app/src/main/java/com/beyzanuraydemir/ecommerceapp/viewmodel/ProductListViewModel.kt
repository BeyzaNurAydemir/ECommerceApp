package com.beyzanuraydemir.ecommerceapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.repos.ProductsRepository
import com.beyzanuraydemir.ecommerceapp.service.ProductAPIService
import com.beyzanuraydemir.ecommerceapp.service.ProductDatabase
import com.beyzanuraydemir.ecommerceapp.util.PrivateSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ProductListViewModel(application: Application) : BaseViewModel(application) {
    val products = MutableLiveData<List<Product>>()
    val productErrorMessage = MutableLiveData<Boolean>()
    val productLoading = MutableLiveData<Boolean>()
    private var updateTime = 0.2 * 60 * 1000 * 1000 * 1000L

    private val productApiService = ProductAPIService()
    private val disposable = CompositeDisposable()
    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())

    private var productsRepo = ProductsRepository(application.applicationContext)

    fun refreshData(){
        val saveTime = privateSharedPreferences.takeTheTime()
        if(saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime){
            //take the sqlite
            getDataFromSQLite()
        }else{
            //take the internet
            getDataFromInternet()
        }

    }

    private fun getDataFromInternet(){
        productLoading.value = true

        disposable.add(
            productApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Product>>(){
                    override fun onSuccess(t: List<Product>) {
                        //Success
                        hideSqlite(t)

                    }

                    override fun onError(e: Throwable) {
                        //Fail
                        productErrorMessage.value = true
                        productLoading.value = false
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun getDataFromSQLite(){
        productLoading.value = true
        launch {
            val productList = ProductDatabase(getApplication()).productDao().getAllProducts()
            showProducts(productList.orEmpty())
            //Toast.makeText(getApplication(),"Ürünleri roomdan aldık",Toast.LENGTH_LONG).show()
        }
    }


/*
    private fun getDataFromSQLite(){
        productsRepo.products()
    }

 */

    private fun showProducts(productsList : List<Product>){
        products.value = productsList
        productErrorMessage.value = false
        productLoading.value = false
    }

    private fun hideSqlite(productList : List<Product>){
        launch {
            val dao = ProductDatabase(getApplication()).productDao()
            dao.deleteAllProducts()
            val uuidList = dao.insertAll(*productList.toTypedArray())
           var i = 0
            while (i < productList.size){
                if (uuidList != null) {
                    //productList[i].uuid = uuidList[i].to
                }
                i = i + 1
            }
            showProducts(productList)
        }
        privateSharedPreferences.saveTime(System.nanoTime())
    }
}