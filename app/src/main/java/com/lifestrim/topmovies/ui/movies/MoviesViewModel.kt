package com.lifestrim.topmovies.ui.movies


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.lifestrim.topmovies.data.repository.MovieRepository

class MoviesViewModel @ViewModelInject constructor(
    repository: MovieRepository
) : ViewModel() {

    val films = repository.getMovies()
}