package br.espartano.moviedbapp.list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.espartano.moviedbapp.R
import br.espartano.moviedbapp.data.Movie
import br.espartano.moviedbapp.extensions.loadGIF
import br.espartano.moviedbapp.list.adapters.MoviesAdapter
import br.espartano.moviedbapp.list.viewmodel.ListMoviesStates
import br.espartano.moviedbapp.list.viewmodel.ListMoviesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListMoviesActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()
    private val recyclerMovies : RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_movies)
    }

    private val toolbar : Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val imgLogo : ImageView by lazy {
        findViewById<ImageView>(R.id.img_loading)
    }

    private val viewModel: ListMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        configureStates()
        initializeViews()
    }

    private fun configureStates() {
        viewModel
            .getState()
            .observe(this, Observer {
                mapActionsForState(it)
        })
        viewModel.getPopularMovies()
    }

    private fun mapActionsForState(state: ListMoviesStates) {
        when (state) {
            is ListMoviesStates.Loading -> showLoadingState()
            is ListMoviesStates.LoadSuccessMovies -> {
                hideLoadingState()
                movies.addAll(state.movies)
                recyclerMovies.adapter?.notifyDataSetChanged()
            }
            is ListMoviesStates.Error -> {
                hideLoadingState()
                showMessage(state.t.message)
            }
        }
    }

    private fun showMessage(text: String?) {
        AlertDialog
            .Builder(this)
            .setMessage(text)
            .setTitle(getString(R.string.system_message))
            .create()
            .show()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar)
        recyclerMovies.layoutManager = GridLayoutManager(this, 2)
        recyclerMovies.adapter = MoviesAdapter(movies)
        imgLogo.loadGIF(R.mipmap.loading)
    }

    private fun showLoadingState() {
        recyclerMovies.visibility = View.GONE
        imgLogo.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        recyclerMovies.visibility = View.VISIBLE
        imgLogo.visibility = View.GONE
    }
}
