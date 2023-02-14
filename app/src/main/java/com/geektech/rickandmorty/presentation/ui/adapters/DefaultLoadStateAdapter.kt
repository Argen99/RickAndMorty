package com.geektech.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geektech.rickandmorty.databinding.ItemLoaderBinding

class DefaultLoadStateAdapter(
    private val tryAgain: () -> Unit
) : LoadStateAdapter<DefaultLoadStateAdapter.CharacterLoadStateHolder>() {

    override fun onBindViewHolder(holder: CharacterLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharacterLoadStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoaderBinding.inflate(inflater, parent, false)
        return CharacterLoadStateHolder(binding)
    }

    inner class CharacterLoadStateHolder(
        private val binding: ItemLoaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            progressBar.isVisible = loadState is LoadState.Loading
        }

        init {
            binding.tryAgainButton.setOnClickListener {
                tryAgain.invoke()
            }
        }
    }
}