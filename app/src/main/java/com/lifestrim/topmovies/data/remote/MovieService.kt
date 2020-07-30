package com.lifestrim.topmovies.data.remote

import com.lifestrim.topmovies.data.entities.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("discover/movie?api_key=e15b57083e8de091e47561d7f1112de0&sort_by=popularity.desc&primary_release_year=2019")
    suspend fun getAllMovies(): Response<MovieList>
}