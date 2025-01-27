package com.example.tema10app1

data class MovieResponse(
    val Search:List<Movie>
)

data class Movie(
    val Title:String,
    val Year:String,
    val Type:String,
    val Poster:String
)
