package com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesharkstudio.allsportsassignment.domain.model.HomeItem
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetHomeItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getHomeItemsUseCase: GetHomeItemsUseCase) : ViewModel() {
    private val _items = MutableStateFlow<List<HomeItem>>(emptyList())
    val items: StateFlow<List<HomeItem>> = _items

    init {
        viewModelScope.launch {
            _items.value = getHomeItemsUseCase()
        }
    }
}
