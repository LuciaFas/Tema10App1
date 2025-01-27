package com.example.tema10app1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apikey:String,
        @Query("s") s:String
    ):Response<MovieResponse>
}