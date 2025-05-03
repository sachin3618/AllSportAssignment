package com.codesharkstudio.allsportsassignment.domain.usecase

import com.codesharkstudio.allsportsassignment.domain.model.Data
import com.codesharkstudio.allsportsassignment.domain.repository.SportRepository
import kotlinx.coroutines.flow.Flow

class GetSportsUseCase(private val repo: SportRepository) {
    operator fun invoke(): Flow<List<Data>> = repo.getSports()
}