package com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesharkstudio.allsportsassignment.domain.model.Profile
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val getProfileUseCase: GetProfileUseCase) : ViewModel() {
    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> = _profile

    init {
        viewModelScope.launch {
            _profile.value = getProfileUseCase()
        }
    }
}
