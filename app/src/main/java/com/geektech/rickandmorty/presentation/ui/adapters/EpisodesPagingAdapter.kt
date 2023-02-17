package com.geektech.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geektech.domain.models.episodes.Episodes
import com.geektech.rickandmorty.databinding.ItemEpisodeBinding

class EpisodesPagingAdapter : PagingDataAdapter<Episodes,
        EpisodesPagingAdapter.EpisodesViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodesViewHolder(
        ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodesViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(episode: Episodes) {
            binding.tvName.text = episode.name
            binding.tvEpisode.text = episode.episode
            binding.tvAirDate.text = episode.air_date
        }

    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Episodes>() {
            override fun areItemsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
                return oldItem == newItem
            }
        }
    }
}