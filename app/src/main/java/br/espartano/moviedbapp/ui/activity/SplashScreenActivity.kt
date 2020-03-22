package br.espartano.moviedbapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import br.espartano.moviedbapp.R
import br.espartano.moviedbapp.list.ui.ListMoviesActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initializeListMovies()
    }


    private fun initializeListMovies() {
        findViewById<ImageView>(R.id.img_logo).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, ListMoviesActivity::class.java))
            finish()
        },
            2000L
        )
    }
}
