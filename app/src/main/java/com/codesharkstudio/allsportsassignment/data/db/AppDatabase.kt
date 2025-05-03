package com.codesharkstudio.allsportsassignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SportEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao
}