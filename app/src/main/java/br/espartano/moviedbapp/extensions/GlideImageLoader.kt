package br.espartano.moviedbapp.extensions

import android.widget.ImageView
import br.espartano.moviedbapp.interfaces.ImageLoader
import com.bumptech.glide.Glide

class GlideImageLoader: ImageLoader {

    override fun load(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    override fun loadGIF(imageView: ImageView, resource: Int) {
        Glide.with(imageView.context)
            .asGif()
            .load(resource)
            .into(imageView)
    }
}