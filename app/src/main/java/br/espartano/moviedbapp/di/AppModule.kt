package br.espartano.moviedbapp.di

import br.espartano.moviedbapp.list.viewmodel.ListMoviesViewModel
import br.espartano.moviedbapp.repository.MoviesNetworkRepository
import br.espartano.moviedbapp.repository.MoviesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModules = module {
    viewModel {
        ListMoviesViewModel(get())
    }

    factory<MoviesRepository> {
        MoviesNetworkRepository()
    }
}