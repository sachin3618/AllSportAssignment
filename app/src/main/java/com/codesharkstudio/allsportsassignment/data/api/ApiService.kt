package com.codesharkstudio.allsportsassignment.data.api

import com.codesharkstudio.allsportsassignment.domain.model.SportsData
import retrofit2.Response
import retrofit2.http.GET

// --- data/api/ApiService.kt ---
interface ApiService {
    @GET("api/mock/get_all_sport_list")
    suspend fun getSports(): Response<SportsData>
}