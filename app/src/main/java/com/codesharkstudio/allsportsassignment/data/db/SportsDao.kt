package com.codesharkstudio.allsportsassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {
    @Query("SELECT * FROM sports")
    fun getAllSports(): Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sports: List<SportEntity>)

    @Query("DELETE FROM sports WHERE sport_id = :sportId")
    suspend fun deleteSport(sportId: Int)
}