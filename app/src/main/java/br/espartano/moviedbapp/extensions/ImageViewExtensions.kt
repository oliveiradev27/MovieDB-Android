package br.espartano.moviedbapp.extensions

import android.widget.ImageView
import br.espartano.moviedbapp.extensions.ImageViewInjector.loader
import br.espartano.moviedbapp.interfaces.ImageLoader
import org.koin.core.KoinComponent
import org.koin.java.KoinJavaComponent.inject

object ImageViewInjector: KoinComponent {
    val loader : ImageLoader by inject(ImageLoader::class.java)
}

fun ImageView.load(url : String) {
    loader.load(this, url)
}

fun ImageView.loadGIF(resource: Int) {
    loader.loadGIF(this, resource)
}