package com.codesharkstudio.allsportsassignment.domain.model

data class SportsData(
    val data: List<Data>,
    val message: String,
    val status: String,
    val statusCode: Int
)