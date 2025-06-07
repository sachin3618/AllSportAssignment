package com.codesharkstudio.allsportsassignment.domain.usecase.dashboard

import com.codesharkstudio.allsportsassignment.domain.model.Message
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepository

class GetMessagesUseCase(private val repository: DashboardRepository) {
    suspend operator fun invoke(): List<Message> = repository.getMessages()
}
