package br.espartano.moviedbapp.repository

import br.espartano.moviedbapp.data.PopularMoviesResponse
import retrofit2.http.GET

interface MoviesRetrofitService {

    @GET("movie/popular?api_key=45d247a5bcfcb079274e3255988d392e")
    suspend fun getPopularMovies(): PopularMoviesResponse

}