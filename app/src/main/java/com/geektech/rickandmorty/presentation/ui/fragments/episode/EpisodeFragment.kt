package com.geektech.rickandmorty.presentation.ui.fragments.episode

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.R
import com.geektech.rickandmorty.core.base.BaseFragment
import com.geektech.rickandmorty.core.extensions.gone
import com.geektech.rickandmorty.core.extensions.simpleScan
import com.geektech.rickandmorty.databinding.FragmentEpisodeBinding
import com.geektech.rickandmorty.presentation.ui.adapters.DefaultLoadStateAdapter
import com.geektech.rickandmorty.presentation.ui.adapters.EpisodesPagingAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel by viewModel<EpisodeViewModel>()

    private val episodesAdapter: EpisodesPagingAdapter by lazy {
        EpisodesPagingAdapter()
    }

    override fun initialize() {
        val footer = DefaultLoadStateAdapter(this::tryAgain)
        binding.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
            adapter = episodesAdapter.withLoadStateFooter(footer)
        }
    }

    override fun setupObservers() {
        scrollingToTopWhenFiltering()

        episodesAdapter.addLoadStateListener { state ->
            binding.progressBar.isVisible = state.source.refresh is LoadState.Loading
            if (state.refresh is LoadState.NotLoading)
                binding.swipeRefreshLayout.isRefreshing = false
            if (binding.swipeRefreshLayout.isRefreshing)
                binding.progressBar.gone()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            episodesAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesFlow.collectLatest { pagingData ->
                episodesAdapter.submitData(pagingData)
            }
        }

        binding.etSearch.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    private fun tryAgain() {
        episodesAdapter.retry()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun scrollingToTopWhenFiltering() = viewLifecycleOwner.lifecycleScope.launch {
        episodesAdapter.loadStateFlow.map { it.refresh }
            .simpleScan(2)
            .collectLatest { (previousState, currentState) ->
                if (previousState is LoadState.Loading && currentState is LoadState.NotLoading)
                    binding.rvEpisodes.scrollToPosition(0)
            }
    }
}