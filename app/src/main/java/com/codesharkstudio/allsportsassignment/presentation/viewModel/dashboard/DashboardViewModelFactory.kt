package com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepository
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetHomeItemsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetMessagesUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetProfileUseCase

class DashboardViewModelFactory(private val repository: DashboardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(GetHomeItemsUseCase(repository)) as T
            }
            modelClass.isAssignableFrom(MessagesViewModel::class.java) -> {
                MessagesViewModel(GetMessagesUseCase(repository)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(GetProfileUseCase(repository)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
