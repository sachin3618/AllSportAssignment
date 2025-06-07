package com.codesharkstudio.allsportsassignment.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codesharkstudio.allsportsassignment.R
import com.codesharkstudio.allsportsassignment.databinding.FragmentProfileBinding
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepositoryImpl
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.DashboardViewModelFactory
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        DashboardViewModelFactory(DashboardRepositoryImpl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        lifecycleScope.launchWhenStarted {
            viewModel.profile.collectLatest { profile ->
                profile?.let {
                    binding.tvName.text = it.name
                    binding.tvBio.text = it.bio
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
