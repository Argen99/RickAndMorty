package com.geektech.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geektech.domain.models.Character
import com.geektech.rickandmorty.core.extensions.loadImage
import com.geektech.rickandmorty.databinding.ItemCharacterBinding

class CharacterPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<Character, CharacterPagingAdapter.CharacterViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: Character) = with(binding) {
            status.text = character.status
            name.text = character.name
            species.text = character.species
            ivImage.loadImage(character.image)
        }
        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
            }
        }
    }
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}
