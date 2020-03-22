package br.espartano.moviedbapp.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.espartano.moviedbapp.R
import br.espartano.moviedbapp.data.Movie
import br.espartano.moviedbapp.network.configs.Configs.BASE_URL_THUMB
import com.bumptech.glide.Glide

class MoviesViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val titleMovie: TextView by lazy { view.findViewById<TextView>(R.id.text_title_movie) }
    private val imageMovie: ImageView by lazy { view.findViewById<ImageView>(R.id.image_movie) }
    private val textReleaseDate: TextView by lazy { view.findViewById<TextView>(R.id.text_release_date) }

    fun bindData(movie: Movie) {
        titleMovie.text = movie.title
        textReleaseDate.text = movie.releaseDate
        val imageUrl = "$BASE_URL_THUMB${movie.posterPath}"
        Glide.with(view)
            .load(imageUrl)
            .into(imageMovie)
    }
}

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =  LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindData(movies[position])
    }
}