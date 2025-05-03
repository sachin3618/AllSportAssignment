package com.codesharkstudio.allsportsassignment.core.util

import com.codesharkstudio.allsportsassignment.domain.model.Data

sealed class UiState {
    object Loading   : UiState()
    data class Success(val data: List<Data>) : UiState()
    data class Error(val message: String)    : UiState()
}