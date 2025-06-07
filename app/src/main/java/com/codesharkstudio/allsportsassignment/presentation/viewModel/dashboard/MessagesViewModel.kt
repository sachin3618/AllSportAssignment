package com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesharkstudio.allsportsassignment.domain.model.Message
import com.codesharkstudio.allsportsassignment.domain.usecase.dashboard.GetMessagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessagesViewModel(private val getMessagesUseCase: GetMessagesUseCase) : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    init {
        viewModelScope.launch {
            _messages.value = getMessagesUseCase()
        }
    }
}
