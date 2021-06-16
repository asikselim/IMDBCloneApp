package com.selimasik.imdbapp.ui.filmListeleme

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.selimasik.imdbapp.data.*
import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.databinding.ActivityFilmLiseteleBinding
import com.selimasik.imdbapp.ui.detay.FilmDetayiActivity

class FilmLiseteleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmLiseteleBinding

    var filmViewModel = FilmViewModel()
    private lateinit var filmAdapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_film_lisetele)
        binding = ActivityFilmLiseteleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
        binding.progressBar.max = 100
        val currentProgress = 100
        ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
                .setDuration(2000)
                .start()
    }

    private fun init() {
        filmAl()
    }
    fun filmAl() {
        filmViewModel.apply {
            filmLiveData.observe(this@FilmLiseteleActivity, Observer {
                filmAdapter= FilmAdapter(it as ArrayList<FilmItem>,object : ItemClickListener {
                    override fun onDelete(position: Int) {
                    }
                    override fun onItemClick(position: Int) {
                        startActivity(Intent(this@FilmLiseteleActivity, FilmDetayiActivity::class.java))
                    }
                })
                binding.rcvFilmler.adapter = filmAdapter
                binding.rcvFilmler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL, false)

                binding.btnGridLayout.setOnClickListener {

                    binding.rcvFilmler.adapter = filmAdapter
                    binding.rcvFilmler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                }

                binding.btnListLayout.setOnClickListener {

                    binding.rcvFilmler.adapter = filmAdapter
                    binding.rcvFilmler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL, false)
                }




                binding.progressBar.setVisibility(View.GONE)

            })
            error.observe(this@FilmLiseteleActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
            loading?.observe(this@FilmLiseteleActivity, Observer {

            })
        }
    }

}