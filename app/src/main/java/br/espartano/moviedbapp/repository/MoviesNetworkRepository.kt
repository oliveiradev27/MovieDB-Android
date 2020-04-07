package br.espartano.moviedbapp.repository

import br.espartano.moviedbapp.data.Movie
import br.espartano.moviedbapp.network.RetrofitNetworkConnector

class MoviesNetworkRepository: MoviesRepository {

    private val service = RetrofitNetworkConnector.getService<MoviesRetrofitService>()

    override suspend fun getPopularMovies(): List<Movie> = service
        .getPopularMovies()
        .results
}