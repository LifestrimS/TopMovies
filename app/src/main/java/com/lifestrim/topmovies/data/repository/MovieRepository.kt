package com.lifestrim.topmovies.data.repository

import com.lifestrim.topmovies.data.local.MovieDao
import com.lifestrim.topmovies.data.remote.MovieRemoteDataSource
import com.lifestrim.topmovies.util.performGetOperation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) {

    fun getMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}