package com.codesharkstudio.allsportsassignment.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesharkstudio.allsportsassignment.core.util.UiState
import com.codesharkstudio.allsportsassignment.domain.usecase.DeleteSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.GetSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.RefreshSportsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SportViewModel(
    private val getSportsUseCase: GetSportsUseCase,
    private val refreshSportsUseCase: RefreshSportsUseCase,
    private val deleteSportUseCase: DeleteSportsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            combine(
                getSportsUseCase(), // Flow from DB
                _query               // Flow of search text
            ) { list, query ->
                val filtered = if (query.isBlank()) list
                else list.filter { it.sport_name.contains(query, ignoreCase = true) }
                UiState.Success(filtered)
            }.collect {
                _uiState.value = it
            }
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val success = refreshSportsUseCase()
                if (!success) _uiState.value = UiState.Error("Failed to refresh")
            } catch (e: Exception) {
                //  _uiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun onSearch(query: String) {
        _query.value = query
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                deleteSportUseCase(id)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Delete error")
            }
        }
    }

    fun refresh() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val result = refreshSportsUseCase()
                if (!result) {
                    _uiState.value = UiState.Error("Error refreshing sports data")
                } else {
                    //  _uiState.value = UiState.Success(emptyList())  // Or populate with success data
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

}
