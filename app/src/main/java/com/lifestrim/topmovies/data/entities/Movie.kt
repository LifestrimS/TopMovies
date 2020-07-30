package com.lifestrim.topmovies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val poster_path: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val popularity: Double
)