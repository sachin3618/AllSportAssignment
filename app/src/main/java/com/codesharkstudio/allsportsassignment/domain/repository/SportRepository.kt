package com.codesharkstudio.allsportsassignment.domain.repository

import com.codesharkstudio.allsportsassignment.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface SportRepository {
    fun getSports(): Flow<List<Data>>
    suspend fun refreshSports()
    suspend fun deleteSport(id: Int)
}