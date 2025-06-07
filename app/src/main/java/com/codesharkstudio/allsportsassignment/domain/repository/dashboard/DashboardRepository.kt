package com.codesharkstudio.allsportsassignment.domain.repository.dashboard

import com.codesharkstudio.allsportsassignment.domain.model.HomeItem
import com.codesharkstudio.allsportsassignment.domain.model.Message
import com.codesharkstudio.allsportsassignment.domain.model.Profile

interface DashboardRepository {
    suspend fun getHomeItems(): List<HomeItem>
    suspend fun getMessages(): List<Message>
    suspend fun getProfile(): Profile
}
