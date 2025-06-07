package com.codesharkstudio.allsportsassignment.data.dummy

import com.codesharkstudio.allsportsassignment.data.dummy.model.HomeItemDto
import com.codesharkstudio.allsportsassignment.data.dummy.model.MessageDto
import com.codesharkstudio.allsportsassignment.data.dummy.model.ProfileDto

class MockApiService : DummyApiService {
    override suspend fun fetchHomeItems(): List<HomeItemDto> = listOf(
        HomeItemDto(1, "Welcome"),
        HomeItemDto(2, "Latest News"),
        HomeItemDto(3, "Events")
    )

    override suspend fun fetchMessages(): List<MessageDto> = listOf(
        MessageDto(1, "Admin", "Hello and welcome"),
        MessageDto(2, "Support", "How can we help?")
    )

    override suspend fun fetchProfile(): ProfileDto =
        ProfileDto(1, "John Doe", "Sample bio for user")
}
