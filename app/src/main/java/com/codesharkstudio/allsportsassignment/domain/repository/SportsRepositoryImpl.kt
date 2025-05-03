package com.codesharkstudio.allsportsassignment.domain.repository

import android.content.Context
import android.net.ConnectivityManager
import com.codesharkstudio.allsportsassignment.data.api.ApiService
import com.codesharkstudio.allsportsassignment.data.db.SportDao
import com.codesharkstudio.allsportsassignment.data.db.SportEntity
import com.codesharkstudio.allsportsassignment.domain.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SportRepositoryImpl(
    private val api: ApiService,
    private val dao: SportDao,
    private val context: Context
) : SportRepository {

    override fun getSports(): Flow<List<Data>> =
        dao.getAllSports().map { list ->
            list.map { entity ->
                Data(
                    sport_id = entity.sport_id,
                    nsrs_sport_id = entity.nsrs_sport_id,
                    rf_sport_db_name = entity.rf_sport_db_name,
                    sport_name = entity.sport_name,
                    status = entity.status
                )
            }
        }

    override suspend fun refreshSports(): Boolean {
        if (!isConnectedToInternet(context)) {
            return false // Return false if there is no internet connection
        }
        return try {
            val response = api.getSports() // Make the API call
            if (response.isSuccessful) {
                response.body()?.data?.let { dtos ->
                    // Map the API response to SportEntity objects
                    val entities = dtos.map { dto ->
                        SportEntity(
                            sport_id = dto.sport_id,
                            nsrs_sport_id = dto.nsrs_sport_id,
                            rf_sport_db_name = dto.rf_sport_db_name,
                            sport_name = dto.sport_name,
                            status = dto.status
                        )
                    }
                    // Insert the mapped entities into the database
                    dao.insertAll(entities)
                    true // Return true if data was successfully inserted
                } ?: false // Return false if response body data is null
            } else {
                false // Return false if API response is unsuccessful
            }
        } catch (e: Exception) {
            // Handle any exception that might occur during the API call or database operation
            false // Return false in case of an exception
        }
    }
    override suspend fun deleteSport(id: Int): Boolean =
        try {
            dao.deleteSport(id)
            true
        } catch (e: Exception) {
            false
        }
}

fun isConnectedToInternet(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnectedOrConnecting == true
}