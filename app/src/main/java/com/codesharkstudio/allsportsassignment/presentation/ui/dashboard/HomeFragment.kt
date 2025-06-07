package com.codesharkstudio.allsportsassignment.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codesharkstudio.allsportsassignment.R
import com.codesharkstudio.allsportsassignment.databinding.FragmentHomeBinding
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepositoryImpl
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.DashboardViewModelFactory
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        DashboardViewModelFactory(DashboardRepositoryImpl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        lifecycleScope.launchWhenStarted {
            viewModel.items.collectLatest { list ->
                binding.tvHome.text = list.joinToString("\n") { it.title }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
