package com.codesharkstudio.allsportsassignment.domain.usecase.dashboard

import com.codesharkstudio.allsportsassignment.domain.model.HomeItem
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepository

class GetHomeItemsUseCase(private val repository: DashboardRepository) {
    suspend operator fun invoke(): List<HomeItem> = repository.getHomeItems()
}
