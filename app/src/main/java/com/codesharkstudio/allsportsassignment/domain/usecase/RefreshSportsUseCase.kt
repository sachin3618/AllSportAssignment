package com.codesharkstudio.allsportsassignment.domain.usecase

import com.codesharkstudio.allsportsassignment.domain.repository.SportRepository

class RefreshSportsUseCase(private val repository: SportRepository) {
    suspend operator fun invoke() = repository.refreshSports()
}