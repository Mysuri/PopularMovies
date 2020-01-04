package com.example.popularmovies.api

class MoviesRepository {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()

    fun getMovies(year: String) = moviesApi.getMovies(year)
}