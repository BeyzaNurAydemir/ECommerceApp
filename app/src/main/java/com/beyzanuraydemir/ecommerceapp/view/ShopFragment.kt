package com.beyzanuraydemir.ecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.adapter.ProductRecyclerAdapter
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentShopBinding
import com.beyzanuraydemir.ecommerceapp.viewmodel.ProductListViewModel


class ShopFragment() : Fragment() {

    private lateinit var binding : FragmentShopBinding
    private lateinit var viewModel : ProductListViewModel
    private val recyclerProductAdapter = ProductRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        viewModel.refreshData()
        binding.productListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productListRecyclerView.adapter = recyclerProductAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.loadingProductsPB.visibility = View.VISIBLE
            binding.productErrorMessageTV.visibility = View.GONE
            binding.productListRecyclerView.visibility = View.GONE
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.products.observe(viewLifecycleOwner, Observer { products->
            products?.let {
                binding.productListRecyclerView.visibility = View.VISIBLE
                recyclerProductAdapter.productListUpdate(products)
                binding.productListRecyclerView.adapter = recyclerProductAdapter
            }
        })

        viewModel.productErrorMessage.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if (it){
                    binding.productErrorMessageTV.visibility = View.VISIBLE
                    binding.productListRecyclerView.visibility = View.GONE
                }else{
                    binding.productErrorMessageTV.visibility = View.GONE
                }
            }
        })

        viewModel.productLoading.observe(viewLifecycleOwner, Observer { looading->
            looading?.let {
                if(it){
                    binding.productListRecyclerView.visibility = View.GONE
                    binding.productErrorMessageTV.visibility = View.GONE
                    binding.loadingProductsPB.visibility = View.VISIBLE
                }else{
                    binding.loadingProductsPB.visibility = View.GONE
                }
            }
        })

    }

}