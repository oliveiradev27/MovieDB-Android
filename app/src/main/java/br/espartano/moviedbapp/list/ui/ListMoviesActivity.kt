package br.espartano.moviedbapp.list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.espartano.moviedbapp.R
import br.espartano.moviedbapp.data.Movie
import br.espartano.moviedbapp.list.adapters.MoviesAdapter
import br.espartano.moviedbapp.list.viewmodel.ListMoviesStates
import br.espartano.moviedbapp.list.viewmodel.ListMoviesViewModel
import br.espartano.moviedbapp.repository.MoviesNetworkRepository

class ListMoviesActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()
    private val recyclerMovies : RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_movies)
    }

    private val toolbar : Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }
    private val viewModel: ListMoviesViewModel by lazy {
        ViewModelProvider(this@ListMoviesActivity,
            ListMoviesViewModel.ListMoviesViewModelFactory(MoviesNetworkRepository()))
            .get(ListMoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        configureStates()
        initializeViews()
    }

    private fun configureStates() {
        viewModel.getState()
            .observe(this, Observer {
            when (it) {
                is ListMoviesStates.Loading -> Toast.makeText(this, "carregando..", Toast.LENGTH_LONG).show()
                is ListMoviesStates.LoadSuccessMovies ->{
                    movies.addAll(it.movies)
                    recyclerMovies.adapter?.notifyDataSetChanged()
                }
                is ListMoviesStates.Error -> Toast.makeText(this, it.t.message, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.getPopularMovies()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar)
        recyclerMovies.layoutManager = GridLayoutManager(this, 2)
        recyclerMovies.adapter = MoviesAdapter(movies)
    }
}
