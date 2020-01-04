package com.example.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        initViews()
    }

    private fun initViews() {
        val movie: Movie = intent.extras?.getParcelable(MOVIE)!!

        tvOverview.movementMethod = ScrollingMovementMethod()
        tvOverview.text = movie.overview
        tvRating.text = movie.rating.toString()
        tvRelease.text = movie.releaseDate
        tvTitle.text = movie.title

        //Smooth scrolling & fast image loading
        Glide.with(this).load(movie.getBackdropImageUrl()).into(ivHeader)
        Glide.with(this).load(movie.getPosterImageUrl()).into(ivPoster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
