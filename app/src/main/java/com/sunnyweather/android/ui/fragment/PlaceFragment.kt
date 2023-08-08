package com.sunnyweather.android.ui.fragment


import android.os.Bundle
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPlaceBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = requireActivity()
            it.placeVM = placeViewModel
            it.setUpItem()
            setBackPress()
        }.root
        return binding
    }


    private fun FragmentPlaceBinding.setUpItem() {
        val placeAdapter = PlaceAdapter(emptyList())

        placeViewModel.placeLiveData.observe(requireActivity()) { result ->

            result.onSuccess {
                placeAdapter.placeList = it
                placeAdapter.notifyItemChanged(0)

            }
        }
        placeAdapter.onClick = placeViewModel::savePlace
        placeRv.adapter = placeAdapter

    }

    private fun setBackPress() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                Toast.makeText(requireContext(), "请输入地址", Toast.LENGTH_SHORT).show()
                return
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)


    }


}