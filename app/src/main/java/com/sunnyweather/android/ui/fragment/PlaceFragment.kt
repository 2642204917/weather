package com.sunnyweather.android.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.sunnyweather.android.databinding.FragmentPlaceBinding
import com.sunnyweather.android.logic.PlaceAdapter
import com.sunnyweather.android.ui.base.BaseFragment
import com.sunnyweather.android.ui.viewmodel.PlaceViewModel

class PlaceFragment : BaseFragment() {

    private val placeViewModel by viewModels<PlaceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeContext(placeViewModel._contextEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPlaceBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.placeVM = placeViewModel
            it.setUpItem()
           // setBackPress()
        }.root
        return binding
    }


    private fun FragmentPlaceBinding.setUpItem() {
        val placeAdapter = PlaceAdapter(emptyList())

        placeViewModel.placelist.observe(viewLifecycleOwner) { result ->

            placeAdapter.placeList = result
            placeAdapter.notifyItemChanged(0)
        }
        placeAdapter.onClick = placeViewModel::savePlace
        placeRv.adapter = placeAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy:place销毁 ")
    }

}