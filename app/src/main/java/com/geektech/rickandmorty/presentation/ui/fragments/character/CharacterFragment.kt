package com.geektech.rickandmorty.presentation.ui.fragments.character

import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
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

    private val characterAdapter: CharacterPagingAdapter by lazy {
        CharacterPagingAdapter(requireContext(),this::onItemClick)
    }

    private var checkedStatusRadioButtonId: Int? = null
    private var checkedGenderRadioButtonId: Int? = null
    private var checkedSpeciesRadioButtonId: Int? = null

    override fun initialize() {
        val footerAdapter = DefaultLoadStateAdapter(this::tryAgain)

        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter.withLoadStateFooter(footerAdapter)
        }
    }

    override fun setupObservers() {

        scrollingToTopWhenFiltering()

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersFlow.collectLatest {
                characterAdapter.submitData(it)
            }
        }

        binding.etSearch.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    override fun setupClickListeners() {
        binding.fabFilter.setOnClickListener {
            val filerBinding = BsFilterBinding.inflate(layoutInflater)
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
            bottomSheet.setContentView(filerBinding.root)
            bottomSheet.show()
            checkedStatusRadioButtonId?.let { filerBinding.rgStatus.check(it) }
            checkedGenderRadioButtonId?.let { filerBinding.rgGender.check(it) }
            checkedSpeciesRadioButtonId?.let { filerBinding.rgSpecies.check(it) }

            filerBinding.apply.setOnClickListener {
                checkedStatusRadioButtonId = filerBinding.rgStatus.checkedRadioButtonId
                checkedGenderRadioButtonId = filerBinding.rgGender.checkedRadioButtonId
                checkedSpeciesRadioButtonId = filerBinding.rgSpecies.checkedRadioButtonId

                val status = getCheckedRadioButtonText(filerBinding, checkedStatusRadioButtonId!!)
                val gender = getCheckedRadioButtonText(filerBinding, checkedGenderRadioButtonId!!)
                val species = getCheckedRadioButtonText(filerBinding, checkedSpeciesRadioButtonId!!)
                viewModel.filterBy(status = status, gender = gender, species = species)
                bottomSheet.dismiss()
            }
            filerBinding.reset.setOnClickListener {
                checkedStatusRadioButtonId = null
                checkedGenderRadioButtonId = null
                checkedSpeciesRadioButtonId = null
                viewModel.filterBy("", "","")
                viewModel.searchBy("")
                binding.etSearch.text = null
                bottomSheet.dismiss()
            }
        }
    }

    private fun getCheckedRadioButtonText(binding: ViewBinding ,checkedRadioButtonId: Int): String? {
        val statusCheckedRadioButton = binding.root.findViewById<RadioButton?>(checkedRadioButtonId)
        return statusCheckedRadioButton?.text?.toString()
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

