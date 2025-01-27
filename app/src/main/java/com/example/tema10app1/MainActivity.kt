package com.example.tema10app1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tema10app1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterMovie: Adapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            getMovies()
        }
    }


    private suspend fun getMovies() {
        val apiKey = "e6399086"
        val filtro = "world"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieApiService::class.java)

        val response = service.getMovies(apikey = apiKey, s = filtro)

        if (response.isSuccessful) {
            val peliculas = response.body()?.Search ?: emptyList()
            
            linearLayoutManager = LinearLayoutManager(this)
            adapterMovie = Adapter(peliculas)

            binding.recycler.apply {
                layoutManager = linearLayoutManager
                adapter = adapterMovie
            }


        } else {
            Log.e("API", "Error: ${response.code()}")
        }
    }
}