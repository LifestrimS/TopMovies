package com.lifestrim.topmovies.data.remote

import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource() {

    suspend fun getMovies() = getResult { movieService.getAllMovies() }
}