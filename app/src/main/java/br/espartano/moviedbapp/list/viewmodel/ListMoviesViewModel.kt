package br.espartano.moviedbapp.list.viewmodel

import androidx.lifecycle.*
import br.espartano.moviedbapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMoviesViewModel(private val repository: MoviesRepository): ViewModel() {

    private val states = MutableLiveData<ListMoviesStates>()

    fun getState() : LiveData<ListMoviesStates> = states

    fun getPopularMovies() {
        states.postValue(ListMoviesStates.Loading)
        viewModelScope.launch {
            withContext(IO) {
                try {
                    delay(5000)
                    states.postValue(ListMoviesStates.LoadSuccessMovies(repository.getPopularMovies()))
                } catch (t: Throwable) {
                    states.postValue(ListMoviesStates.Error(t))
                }
            }
        }
    }
}