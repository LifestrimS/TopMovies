package com.lifestrim.topmovies.data.entities

data class MovieList(
    val page: Int,
    val total_result: Int,
    val total_pages: Int,
    val results: List<Movie>
)