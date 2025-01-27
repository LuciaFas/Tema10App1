package com.example.tema10app1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tema10app1.databinding.ItemListMovieBinding
import com.squareup.picasso.Picasso

class Adapter(private val peliculas: List<Movie>):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemListMovieBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculas[position]

        with(holder) {
            binding.txtTitulo.text = pelicula.Title
            binding.txtAnyo.text = pelicula.Year
            binding.txtTipo.text = pelicula.Type

            val imageView: ImageView = binding.imagen

            val imageUrl = pelicula.Poster

            Picasso.get()
                .load(imageUrl)
                .resize(60, 60)
                .centerCrop()
                .into(imageView)
        }
    }
}