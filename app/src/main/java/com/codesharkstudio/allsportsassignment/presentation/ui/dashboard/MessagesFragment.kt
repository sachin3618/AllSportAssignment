package com.codesharkstudio.allsportsassignment.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codesharkstudio.allsportsassignment.R
import com.codesharkstudio.allsportsassignment.databinding.FragmentMessagesBinding
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepositoryImpl
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.DashboardViewModelFactory
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.MessagesViewModel
import kotlinx.coroutines.flow.collectLatest

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MessagesViewModel by viewModels {
        DashboardViewModelFactory(DashboardRepositoryImpl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessagesBinding.bind(view)
        lifecycleScope.launchWhenStarted {
            viewModel.messages.collectLatest { list ->
                binding.tvMessages.text = list.joinToString("\n") { "${'$'}{it.from}: ${'$'}{it.body}" }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
