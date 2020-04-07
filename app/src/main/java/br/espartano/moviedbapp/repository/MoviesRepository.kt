package br.espartano.moviedbapp.repository

import br.espartano.moviedbapp.data.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(): List<Movie>
}