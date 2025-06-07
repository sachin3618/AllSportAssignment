package com.codesharkstudio.allsportsassignment.domain.usecase.dashboard

import com.codesharkstudio.allsportsassignment.domain.model.Profile
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepository

class GetProfileUseCase(private val repository: DashboardRepository) {
    suspend operator fun invoke(): Profile = repository.getProfile()
}
