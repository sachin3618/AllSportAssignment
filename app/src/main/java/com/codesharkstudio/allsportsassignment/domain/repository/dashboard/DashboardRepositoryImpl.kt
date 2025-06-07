package com.codesharkstudio.allsportsassignment.domain.repository.dashboard

import com.codesharkstudio.allsportsassignment.data.dummy.DummyApiService
import com.codesharkstudio.allsportsassignment.data.dummy.MockApiService
import com.codesharkstudio.allsportsassignment.domain.model.HomeItem
import com.codesharkstudio.allsportsassignment.domain.model.Message
import com.codesharkstudio.allsportsassignment.domain.model.Profile

class DashboardRepositoryImpl(
    private val api: DummyApiService = MockApiService()
) : DashboardRepository {
    override suspend fun getHomeItems(): List<HomeItem> =
        api.fetchHomeItems().map { HomeItem(it.id, it.title) }

    override suspend fun getMessages(): List<Message> =
        api.fetchMessages().map { Message(it.id, it.from, it.body) }

    override suspend fun getProfile(): Profile =
        api.fetchProfile().let { Profile(it.id, it.name, it.bio) }
}
