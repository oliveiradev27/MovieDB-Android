package br.espartano.moviedbapp.list.viewmodel

import androidx.lifecycle.*
import br.espartano.moviedbapp.data.Movie
import br.espartano.moviedbapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val states = MutableLiveData<ListMoviesStates>()

    fun getState(): LiveData<ListMoviesStates> = states

    fun getPopularMovies() {
        states.postValue(ListMoviesStates.Loading)
        viewModelScope.launch(IO) {
            try {
               configureSuccessState(repository.getPopularMovies())
            } catch (t: Throwable) {
                states.postValue(ListMoviesStates.Error(t))
            }
        }
    }

    private fun configureSuccessState(response: List<Movie>) {
        if (response.isNotEmpty()) {
            states.postValue(ListMoviesStates.LoadSuccessMovies(response))
        } else {
            states.postValue(ListMoviesStates.EmptyState)
        }
    }
}