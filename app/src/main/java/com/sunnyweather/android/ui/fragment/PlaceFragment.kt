package com.sunnyweather.android.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sunnyweather.android.databinding.FragmentPlaceBinding
import com.sunnyweather.android.logic.adapter.PlaceAdapter
import com.sunnyweather.android.ui.base.BaseFragment
import com.sunnyweather.android.ui.vm.PlaceViewModel

class PlaceFragment : BaseFragment() {

    private val placeViewModel by viewModels<PlaceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeContext(placeViewModel.contextEvent)
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

}