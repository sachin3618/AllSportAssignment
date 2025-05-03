package com.codesharkstudio.allsportsassignment.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesharkstudio.allsportsassignment.domain.repository.SportRepository
import com.codesharkstudio.allsportsassignment.domain.usecase.DeleteSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.GetSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.RefreshSportsUseCase

class SportViewModelFactory(
    private val repository: SportRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SportViewModel::class.java)) {
            return SportViewModel(
                GetSportsUseCase(repository),
                RefreshSportsUseCase(repository),
                DeleteSportsUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
