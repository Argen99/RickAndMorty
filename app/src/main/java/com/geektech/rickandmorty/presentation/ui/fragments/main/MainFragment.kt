package com.geektech.rickandmorty.presentation.ui.fragments.main

import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.R
import com.geektech.rickandmorty.core.base.BaseFragment
import com.geektech.rickandmorty.databinding.FragmentMainBinding
import com.geektech.rickandmorty.presentation.ui.adapters.FragmentPagerAdapter
import com.geektech.rickandmorty.presentation.ui.fragments.character.CharacterFragment
import com.geektech.rickandmorty.presentation.ui.fragments.episode.EpisodeFragment
import com.geektech.rickandmorty.presentation.ui.fragments.location.LocationFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by viewModel<MainViewModel>()

    override fun initialize() {
        val pagerAdapter = FragmentPagerAdapter(requireActivity())

        pagerAdapter.addFragment(
            EpisodeFragment(),
            "Episode"
        )
        pagerAdapter.addFragment(
            CharacterFragment(),
            "Character"
        )
        pagerAdapter.addFragment(
            LocationFragment(),
            "Location"
        )
        binding.fragmentPager.apply {
            adapter = pagerAdapter
            currentItem = 1
        }

        TabLayoutMediator(binding.tabLayout, binding.fragmentPager) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()
    }
}