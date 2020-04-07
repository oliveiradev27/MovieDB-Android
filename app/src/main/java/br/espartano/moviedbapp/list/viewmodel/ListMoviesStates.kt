package br.espartano.moviedbapp.list.viewmodel

import br.espartano.moviedbapp.data.Movie

sealed class ListMoviesStates {
    data class LoadSuccessMovies(val movies: List<Movie>) : ListMoviesStates()
    data class Error(val t: Throwable): ListMoviesStates()
    object EmptyState : ListMoviesStates()
    object Loading : ListMoviesStates()
}