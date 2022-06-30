package com.beyzanuraydemir.ecommerceapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.adapter.BagAdapter
import com.beyzanuraydemir.ecommerceapp.databinding.FragmentBagBinding
import com.beyzanuraydemir.ecommerceapp.model.CRUDResponse
import com.beyzanuraydemir.ecommerceapp.model.Product
import com.beyzanuraydemir.ecommerceapp.model.ProductResponse
import com.beyzanuraydemir.ecommerceapp.repos.ProductsRepository
import com.beyzanuraydemir.ecommerceapp.service.ApiUtils
import com.beyzanuraydemir.ecommerceapp.viewmodel.BagFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BagFragment : Fragment() {
    private var _binding : FragmentBagBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BagFragmentViewModel(requireContext()) }

    private val productsBagAdapter by lazy { BagAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bag, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserves()

        with(binding){
            bagRecyclerView.setHasFixedSize(true)
            productsBagAdapter.onRemoveBagClick = {

            }
        }

    }

    private fun initObserves(){
        with(binding){
            with(viewModel){

                isLoading.observe(viewLifecycleOwner){
                    if (!it) productsLoadingView.visibility = GONE
                }

                productsBag.observe(viewLifecycleOwner){ list->
                    productsBagAdapter.updateList(list)
                    binding.bagRecyclerView.adapter = productsBagAdapter
                    binding.productsLoadingView.visibility = GONE
                    if(list.isNotEmpty()){
                        buyButton.setOnClickListener {
                            it.findNavController().navigate(R.id.action_bagFragment_to_successFragment)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}