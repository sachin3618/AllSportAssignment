package com.codesharkstudio.allsportsassignment.presentation.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.codesharkstudio.allsportsassignment.databinding.ActivityDashboardBinding
import com.codesharkstudio.allsportsassignment.domain.repository.dashboard.DashboardRepositoryImpl
import com.codesharkstudio.allsportsassignment.presentation.viewModel.dashboard.DashboardViewModelFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val repository = DashboardRepositoryImpl()
    private val factory by lazy { DashboardViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.fragmentContainer.id, HomeFragment())
            }
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                com.codesharkstudio.allsportsassignment.R.id.nav_home -> HomeFragment()
                com.codesharkstudio.allsportsassignment.R.id.nav_messages -> MessagesFragment()
                com.codesharkstudio.allsportsassignment.R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.commit {
                replace(binding.fragmentContainer.id, fragment)
            }
            true
        }
    }
}
