package br.espartano.moviedbapp.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.espartano.moviedbapp.InstantCoroutineDispatcherRule
import br.espartano.moviedbapp.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListMoviesViewModelTest {

    @get:Rule
    val coroutine = InstantCoroutineDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `getPopularMovies - must be success state`() = runBlockingTest {
        val (viewModel, dependencies) = createMocks()
        val repository = dependencies.repository
        val observer = mockk<Observer<ListMoviesStates>>(relaxed = true)
        viewModel.getState().observeForever(observer)

        coEvery { repository.getPopularMovies() } returns listOf(mockk(relaxed = true))

        viewModel.getPopularMovies()

        coVerifySequence {
            observer.onChanged(ListMoviesStates.Loading)
            observer.onChanged(ofType(ListMoviesStates.LoadSuccessMovies::class))
        }
    }

    @Test
    fun `getPopularMovies - must be empty state`() = runBlockingTest {
        val (viewModel, dependencies) = createMocks()
        val repository = dependencies.repository
        val observer = mockk<Observer<ListMoviesStates>>(relaxed = true)
        viewModel.getState().observeForever(observer)

        coEvery { repository.getPopularMovies() } returns listOf()

        viewModel.getPopularMovies()

        coVerifySequence {
            observer.onChanged(ListMoviesStates.Loading)
            observer.onChanged(ListMoviesStates.EmptyState)
        }
    }

    @Test
    fun `getPopularMovies - must be error state`() = runBlockingTest {
        val (viewModel, dependencies) = createMocks()
        val repository = dependencies.repository
        val observer = mockk<Observer<ListMoviesStates>>(relaxed = true)
        viewModel.getState().observeForever(observer)
        val error = Throwable("")

        coEvery { repository.getPopularMovies() } throws error

        viewModel.getPopularMovies()

        coVerifySequence {
            observer.onChanged(ListMoviesStates.Loading)
            observer.onChanged(eq(ListMoviesStates.Error(error)))
        }
    }

    private fun createMocks(): Pair<ListMoviesViewModel, ListMoviesViewModelDependencies> {
        val repository = mockk<MoviesRepository>(relaxed = true)
        val viewModel = ListMoviesViewModel(repository)
        return Pair(viewModel, ListMoviesViewModelDependencies(repository))
    }
}

data class ListMoviesViewModelDependencies(
    val repository: MoviesRepository
)