package br.espartano.moviedbapp.network

import br.espartano.moviedbapp.network.configs.Configs.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitNetworkConnector {

    private val logging =  HttpLoggingInterceptor()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(configureHttpClient())
        .build()

    private fun configureHttpClient() = OkHttpClient.Builder().apply {
        logging.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logging)
    }.build()


    inline fun <reified T> getService() : T = retrofit.create(T::class.java)
}