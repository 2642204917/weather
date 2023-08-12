package com.sunnyweather.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sunnyweather.android.databinding.FragmentStartBinding
import com.sunnyweather.android.ui.base.BaseFragment
import com.sunnyweather.android.ui.viewmodel.StartViewModel

class StartFragment : BaseFragment() {

    private val startViewModel by viewModels<StartViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentStartBinding.inflate(
            inflater,
            container,
            false
        ).also {
            startViewModel.getSavePlace()
            observeContext(startViewModel._contextEvent)
        }.root

    }

}