package com.codesharkstudio.allsportsassignment.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey val sport_id: Int,
    val nsrs_sport_id: Int,
    val rf_sport_db_name: String,
    val sport_name: String,
    val status: String
)