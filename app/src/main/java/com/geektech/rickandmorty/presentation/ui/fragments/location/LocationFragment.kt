package com.geektech.rickandmorty.presentation.ui.fragments.location

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.R
import com.geektech.rickandmorty.core.base.BaseFragment
import com.geektech.rickandmorty.core.extensions.gone
import com.geektech.rickandmorty.databinding.FragmentLocationBinding
import com.geektech.rickandmorty.presentation.ui.adapters.DefaultLoadStateAdapter
import com.geektech.rickandmorty.presentation.ui.adapters.LocationPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<FragmentLocationBinding,
        LocationViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel by viewModel<LocationViewModel>()

    private val locationAdapter: LocationPagingAdapter by lazy {
        LocationPagingAdapter()
    }

    override fun initialize() {
        val footer = DefaultLoadStateAdapter(this::tryAgain)
        binding.rvLocations.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
            adapter = locationAdapter.withLoadStateFooter(footer)
        }
    }

    override fun setupObservers() {
        locationAdapter.addLoadStateListener { state ->
            binding.progressBar.isVisible = state.source.refresh is LoadState.Loading
            if (state.refresh is LoadState.NotLoading)
                binding.swipeRefreshLayout.isRefreshing = false
            if (binding.swipeRefreshLayout.isRefreshing)
                binding.progressBar.gone()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            locationAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationsFlow.collectLatest {
                locationAdapter.submitData(it)
            }
        }

        binding.etSearch.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    private fun tryAgain(){
        locationAdapter.retry()
    }

}