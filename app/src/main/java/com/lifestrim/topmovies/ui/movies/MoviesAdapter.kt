package com.lifestrim.topmovies.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifestrim.topmovies.R
import com.lifestrim.topmovies.data.entities.Movie
import com.lifestrim.topmovies.util.DateConverter
import com.lifestrim.topmovies.util.NotificationUtils
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlin.collections.ArrayList

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<Movie>()

    fun setItems(items: ArrayList<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var movie: Movie
    private val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500/"

    @SuppressLint("SetTextI18n")
    fun bind(item: Movie) {
        this.movie = item

        view.tv_schedule_viewing.setOnClickListener {
            NotificationUtils().setDateTimeForNotification(view, item.title)
        }

        view.title.text = item.title
        view.popularity.text = item.popularity.toString()
        view.release_date.text = DateConverter().getDate(item.release_date)
        view.overview.text = item.overview

        Glide.with(view)
            .load(BASE_URL_IMG + item.poster_path)
            .into(view.poster)

    }

}
