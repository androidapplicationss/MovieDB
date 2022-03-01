package com.bhavesh.moviedb.retrofit

import com.bhavesh.moviedb.model.MovieResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String?): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") api_key: String?): Call<MovieResponse>
}

