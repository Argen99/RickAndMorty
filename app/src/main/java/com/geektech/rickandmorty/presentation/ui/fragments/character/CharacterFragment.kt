package com.geektech.rickandmorty.presentation.ui.fragments.character

import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.R
import com.geektech.rickandmorty.core.base.BaseFragment
import com.geektech.rickandmorty.core.extensions.gone
import com.geektech.rickandmorty.core.extensions.showToast
import com.geektech.rickandmorty.core.extensions.simpleScan
import com.geektech.rickandmorty.databinding.BsFilterBinding
import com.geektech.rickandmorty.databinding.FragmentCharacterBinding
import com.geektech.rickandmorty.presentation.ui.adapters.CharacterPagingAdapter
import com.geektech.rickandmorty.presentation.ui.adapters.DefaultLoadStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<FragmentCharacterBinding,
        CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel by viewModel<CharacterViewModel>()

    private var checkedRadioButtonId: Int? = null
    private val characterAdapter: CharacterPagingAdapter by lazy {
        CharacterPagingAdapter(requireContext(),this::onItemClick)
    }

    override fun setupRequest() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersFlow.collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun initialize() {
        val footerAdapter = DefaultLoadStateAdapter(this::tryAgain)

        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter.withLoadStateFooter(footerAdapter)
        }
    }

    override fun setupObservers() {
        characterAdapter.addLoadStateListener { state ->
            binding.pBar.isVisible = state.source.refresh is LoadState.Loading
            if (state.refresh is LoadState.NotLoading)
                binding.swipeRefreshLayout.isRefreshing = false
            if (binding.swipeRefreshLayout.isRefreshing)
                binding.pBar.gone()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            characterAdapter.refresh()
        }

        binding.fabFilter.setOnClickListener {
            val filerBinding = BsFilterBinding.inflate(layoutInflater)
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
            bottomSheet.setContentView(filerBinding.root)
            bottomSheet.show()
            checkedRadioButtonId?.let { filerBinding.radioGroup.check(it)}

            filerBinding.apply.setOnClickListener {
                checkedRadioButtonId = filerBinding.radioGroup.checkedRadioButtonId
                val checkedRadioButton = filerBinding.root.findViewById<RadioButton?>(
                    checkedRadioButtonId!!
                )
                viewModel.filterBy(checkedRadioButton?.text.toString())
                bottomSheet.dismiss()
            }
            filerBinding.reset.setOnClickListener {
                checkedRadioButtonId = null
                viewModel.filterBy("")
                bottomSheet.dismiss()
            }
        }

        binding.etSearch.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }

        scrollingToTopWhenFiltering()
    }

    private fun onItemClick(id: String) {
        context?.showToast(id)
    }

    private fun tryAgain() {
        characterAdapter.retry()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun scrollingToTopWhenFiltering() = viewLifecycleOwner.lifecycleScope.launch {
        characterAdapter.loadStateFlow.map { it.refresh }
            .simpleScan(2)
            .collectLatest { (previousState, currentState) ->
                if (previousState is LoadState.Loading && currentState is LoadState.NotLoading)
                    binding.rvCharacters.scrollToPosition(0)
            }
    }
}

