package com.geektech.domain.repository

import androidx.paging.PagingData
import com.geektech.domain.models.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

//    fun getAllCharacters(page: Int?, status: String?): Flow<PagingData<Character>>

    fun getPagingData(name: String?,status: String?): Flow<PagingData<Character>>
}