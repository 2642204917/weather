package com.sunnyweather.android.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.sunnyweather.android.R
import com.sunnyweather.android.databinding.FragmentPlaceBinding
import com.sunnyweather.android.logic.PlaceAdapter
import com.sunnyweather.android.ui.base.BaseFragment
import com.sunnyweather.android.ui.viewmodel.PlaceViewModel

class PlaceFragment : BaseFragment() {
    private val viewModelProvider by lazy { ViewModelProvider(this) }

    private val placeViewModel: PlaceViewModel by lazy { viewModelProvider[PlaceViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPlaceBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = requireActivity()
            it.placeVM = placeViewModel
            it.setUpItem()
        }.root
        observeContext(placeViewModel._contextEvent)
        return binding
    }


    private fun FragmentPlaceBinding.setUpItem() {
        val placeAdapter = PlaceAdapter(emptyList())

        placeViewModel.placeLiveData.observe(requireActivity()) { result ->

            result.onSuccess {
                placeAdapter.placeList = it
                placeAdapter.notifyDataSetChanged()

            }

            result.onFailure {


            }

        }
        placeAdapter.onClick = placeViewModel::savePlace
        placeRv.adapter = placeAdapter

    }


}