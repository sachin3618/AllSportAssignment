package com.codesharkstudio.allsportsassignment.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.codesharkstudio.allsportsassignment.R
import com.codesharkstudio.allsportsassignment.core.util.UiState
import com.codesharkstudio.allsportsassignment.data.api.ApiService
import com.codesharkstudio.allsportsassignment.data.db.AppDatabase
import com.codesharkstudio.allsportsassignment.databinding.ActivityMainBinding
import com.codesharkstudio.allsportsassignment.domain.repository.SportRepositoryImpl
import com.codesharkstudio.allsportsassignment.domain.usecase.DeleteSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.GetSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.RefreshSportsUseCase
import com.codesharkstudio.allsportsassignment.presentation.ui.adapter.SportAdapter
import com.codesharkstudio.allsportsassignment.presentation.viewModel.SportViewModel
import com.codesharkstudio.allsportsassignment.presentation.viewModel.SportViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SportViewModel
    private lateinit var adapter: SportAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val repository = SportRepositoryImpl(
            api = Retrofit.Builder()
                .baseUrl("https://admin.37nationalgamesgoa.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java),
            dao = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "sports-db").build().sportDao(),
            context = applicationContext
        )

        val factory = SportViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SportViewModel::class.java]

        adapter = SportAdapter { id -> viewModel.delete(id) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->

                binding.swipeRefreshLayout.isRefreshing = false
                when (state) {
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        adapter.submitList(state.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.text = state.message
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.searchView.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.onSearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }
}