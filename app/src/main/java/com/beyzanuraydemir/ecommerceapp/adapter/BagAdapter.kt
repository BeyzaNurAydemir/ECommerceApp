package com.beyzanuraydemir.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beyzanuraydemir.ecommerceapp.databinding.BagRecyclerRowBinding
import com.beyzanuraydemir.ecommerceapp.model.ProductBagRoomModel
import com.squareup.picasso.Picasso

class BagAdapter : RecyclerView.Adapter<BagAdapter.BagBasketItemDesign>() {
    private val productsBagList = ArrayList<ProductBagRoomModel>()

    inner class BagBasketItemDesign(private var bagRecyclerRow: BagRecyclerRowBinding) :
    RecyclerView.ViewHolder(bagRecyclerRow.root){

        fun bind(productBag: ProductBagRoomModel){
            bagRecyclerRow.apply {
                productModel = productBag

                productBag.image.let {
                    Picasso.get().load(it).into(bagImageView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagBasketItemDesign {
        val bagRecyclerRowBinding = BagRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BagBasketItemDesign(bagRecyclerRowBinding)
    }

    override fun onBindViewHolder(holder: BagBasketItemDesign, position: Int) {
        holder.bind(productsBagList[position])
    }

    override fun getItemCount(): Int {
        return productsBagList.size
    }
}