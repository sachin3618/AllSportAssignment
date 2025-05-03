package com.codesharkstudio.allsportsassignment.domain.usecase

import com.codesharkstudio.allsportsassignment.domain.repository.SportRepository

class DeleteSportsUseCase(private val repository: SportRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteSport(id)
}