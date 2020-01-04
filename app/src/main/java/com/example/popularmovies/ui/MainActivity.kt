package com.example.popularmovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MovieAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

const val MOVIE = "MOVIE"

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) { movie ->
        startDetailActivity(movie)
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        //Movie observer
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })

        //Error observer
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            if(etYear.text.isNullOrEmpty()){
                Toast.makeText(this,"Please fill in a year!",Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getMovies(etYear.text.toString())
            }
        }

        rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun startDetailActivity(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }
}
