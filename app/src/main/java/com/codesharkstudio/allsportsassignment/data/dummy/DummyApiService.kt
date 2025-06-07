package com.codesharkstudio.allsportsassignment.data.dummy

import com.codesharkstudio.allsportsassignment.data.dummy.model.HomeItemDto
import com.codesharkstudio.allsportsassignment.data.dummy.model.MessageDto
import com.codesharkstudio.allsportsassignment.data.dummy.model.ProfileDto

interface DummyApiService {
    suspend fun fetchHomeItems(): List<HomeItemDto>
    suspend fun fetchMessages(): List<MessageDto>
    suspend fun fetchProfile(): ProfileDto
}
