package br.espartano.moviedbapp.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val voteAverage: Float,
    val popularity: Float,
    val video: Boolean,
    val originalLanguage: String,
    val voteCount:  Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val genreIds: List<Int>
)