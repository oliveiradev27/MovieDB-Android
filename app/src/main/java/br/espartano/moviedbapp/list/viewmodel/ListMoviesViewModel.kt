package br.espartano.moviedbapp.list.viewmodel

import androidx.lifecycle.*
import br.espartano.moviedbapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMoviesViewModel(private val repository: MoviesRepository): ViewModel() {

    private val states = MutableLiveData<ListMoviesStates>()

    fun getState() : LiveData<ListMoviesStates> = states

    fun getPopularMovies() {
        viewModelScope.launch {
            withContext(IO) {
                try {
                    states.postValue(ListMoviesStates.LoadSuccessMovies(repository.getPopularMovies()))
                } catch (t: Throwable) {
                    states.postValue(ListMoviesStates.Error(t))
                }
            }
        }
    }

    class ListMoviesViewModelFactory(private val repository: MoviesRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass
                .getConstructor(MoviesRepository::class.java)
                .newInstance(repository)
    }
}