package br.espartano.moviedbapp.interfaces

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, url: String)
    fun loadGIF(imageView: ImageView,resource: Int)
}