package com.bhavesh.moviedb.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.moviedb.R
import com.bhavesh.moviedb.adapter.MovieAdapter
import com.bhavesh.moviedb.model.MovieResponse
import com.card.visitingcardscanner.utils.Constant
import com.education.competitionapp.utils.NetworkCheck
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    var isPopular = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCheck()
        apiIntegration()

    }

    private fun networkCheck() {
        if (NetworkCheck.getNetworkStatus(this)) {
            Log.e("TAG", "Network is On: " )
        }
        else
        {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun apiIntegration() {
        if (NetworkCheck.getNetworkStatus(this)) {
            if (isPopular) setupRemoteTopRated() else setupRemotePopular()
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onResume() {
        super.onResume()
        if (isPopular) setupRemoteTopRated() else setupRemotePopular()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemTopRated -> {
                if (!isPopular) Toast.makeText(
                    applicationContext,
                    "Top Rated Movie.",
                    Toast.LENGTH_LONG
                ).show()
                isPopular = false
                setupRemoteTopRated()
                true
            }
            R.id.itemPopular -> {
                if (isPopular) Toast.makeText(
                    applicationContext,
                    "Popular Movie",
                    Toast.LENGTH_LONG
                ).show()
                isPopular = true
                setupRemotePopular()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRemoteTopRated() {
        val call: Call<MovieResponse> = apiService.getTopRatedMovies(Constant.API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieRequestModels: List<MovieResponse.MovieRequestModel?>? =
                    response.body()?.getResults
                if (movieRequestModels?.size!! > 0) {
                    pb_main.setVisibility(View.GONE)
                    Log.d("Debug: ", "Top Rate Movie " + movieRequestModels?.size)
                }
                val recyclerView = findViewById<View>(R.id.rv_main) as RecyclerView
                recyclerView.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager =
                    GridLayoutManager(applicationContext, 2)
                recyclerView.layoutManager = layoutManager
                val arrMovieRequestModel: List<MovieResponse.MovieRequestModel?>? =
                    response.body()?.getResults
                val adapter = MovieAdapter(
                    this@MainActivity, arrMovieRequestModel

                )
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun setupRemotePopular() {
        val call: Call<MovieResponse> = apiService.getPopularMovies(Constant.API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieRequestModels: List<MovieResponse.MovieRequestModel?>? =
                    response.body()?.getResults
                if (movieRequestModels?.size!! > 0) {
                    pb_main.setVisibility(View.GONE)
                    Log.d("Debug: ", "Movie Popular " + movieRequestModels.size)
                }
                val recyclerView = findViewById<View>(R.id.rv_main) as RecyclerView
                recyclerView.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager =
                    GridLayoutManager(applicationContext, 2)
                recyclerView.layoutManager = layoutManager
                val arrMovieRequestModel: List<MovieResponse.MovieRequestModel?>? =
                    response.body()?.getResults
                val adapter = MovieAdapter(
                    this@MainActivity, arrMovieRequestModel
                )
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }
}