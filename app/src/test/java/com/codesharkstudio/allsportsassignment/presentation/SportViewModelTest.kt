package com.codesharkstudio.allsportsassignment.presentation

import com.codesharkstudio.allsportsassignment.core.util.UiState
import com.codesharkstudio.allsportsassignment.domain.model.Data
import com.codesharkstudio.allsportsassignment.domain.usecase.DeleteSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.GetSportsUseCase
import com.codesharkstudio.allsportsassignment.domain.usecase.RefreshSportsUseCase
import com.codesharkstudio.allsportsassignment.presentation.viewModel.SportViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class SportViewModelTest {

    private val getSportsUseCase: GetSportsUseCase = mock()
    private val refreshSportsUseCase: RefreshSportsUseCase = mock()
    private val deleteSportUseCase: DeleteSportsUseCase = mock()

    private lateinit var viewModel: SportViewModel

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        val dummySports = listOf(
            Data(1, "101", 72, "Cricket", "Active"),
            Data(2, "102", 42, "Football", "Active")
        )

        whenever(getSportsUseCase()).thenReturn(flowOf(dummySports))

        viewModel = SportViewModel(getSportsUseCase, refreshSportsUseCase, deleteSportUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onSearch filters sports list`() = runTest {
        viewModel.onSearch("foot")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
        assert((state as UiState.Success).data.size == 1)
        assert(state.data[0].sport_name == "Football")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete success triggers refresh`() = runTest {
        whenever(deleteSportUseCase(1)).thenReturn(true)
        whenever(refreshSportsUseCase()).thenReturn(true)

        viewModel.delete(1)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
    }
}
