package com.beyzanuraydemir.ecommerceapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.databinding.ProductRecyclerRowBinding
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.util.doPlaceHolder
import com.beyzanuraydemir.ecommerceapp.util.downloadImage
import com.beyzanuraydemir.ecommerceapp.view.BagFragment
import com.beyzanuraydemir.ecommerceapp.view.ShopFragmentDirections

class ProductRecyclerAdapter(val productList : ArrayList<Product>) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>(), ProductClickListener{

    class ProductViewHolder(var view: ProductRecyclerRowBinding) : RecyclerView.ViewHolder(view.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductRecyclerRowBinding>(inflater,R.layout.product_recycler_row,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.view.product = productList[position]
        holder.view.productImage.setOnClickListener {
            println("Calisti.")
            val action = ShopFragmentDirections.actionShopFragmentToProductCardFragment(productList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun productListUpdate(newProductList : List<Product>){
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    override fun productClicked(view: View,product: Product) {
        val action = ShopFragmentDirections.actionShopFragmentToProductCardFragment(product)
        Navigation.findNavController(view).navigate(action)
    }
}