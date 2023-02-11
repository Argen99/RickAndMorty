package com.geektech.rickandmorty.presentation.ui.fragments.main

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.R
import com.geektech.rickandmorty.core.base.BaseFragment
import com.geektech.rickandmorty.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by viewModel<MainViewModel>()

    override fun initialize() {
        super.initialize()
        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
    }

}