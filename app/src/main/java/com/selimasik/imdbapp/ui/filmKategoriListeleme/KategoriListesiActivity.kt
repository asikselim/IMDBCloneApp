package com.selimasik.imdbapp.ui.filmKategoriListeleme

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.*
import com.selimasik.imdbapp.R
import com.selimasik.imdbapp.utils.AlertDialogUtil
import com.selimasik.imdbapp.data.ItemClickListener
import com.selimasik.imdbapp.data.model.FilmKategoriItem
import com.selimasik.imdbapp.databinding.ActivityKategoriListesiBinding
import com.selimasik.imdbapp.ui.filmListeleme.FilmLiseteleActivity


class KategoriListesiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategoriListesiBinding
    var filmKategorileri : List<FilmKategoriItem>?=null
    lateinit var reference: DatabaseReference
    var filmKategoriViewModel = FilmKategoriViewModel()
    private lateinit var filmKategoriAdapter: FilmKategoriAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_kategori_listesi)
        binding = ActivityKategoriListesiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
        binding.progressBar.max = 100
        val currentProgress = 100
        ObjectAnimator.ofInt(binding.progressBar, resources.getString(R.string.progress), currentProgress)
                .setDuration(2000)
                .start()

    }
    private fun init(){
        arama()
        filmKategorileriAl()
    }
    fun filmKategorileriAl() {
        filmKategoriViewModel.apply {
            filmKategorilerLiveData.observe(this@KategoriListesiActivity, Observer {
                filmKategorileri = it
                filmKategorileriListele(it)
            })
            error.observe(this@KategoriListesiActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
            loading?.observe(this@KategoriListesiActivity, Observer {

            })
        }
    }
    fun filmKategorileriListele(filmler:List<FilmKategoriItem>){

        filmKategoriAdapter= FilmKategoriAdapter(filmler as ArrayList<FilmKategoriItem>,object : ItemClickListener {
            override fun onDelete(position: Int) {
            }
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@KategoriListesiActivity, FilmLiseteleActivity::class.java))
            }
        })
        binding.rcvFilmKategoriler.adapter = filmKategoriAdapter
        binding.rcvFilmKategoriler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.progressBar.setVisibility(View.GONE)

    }

    fun adaGoreAra(kategoriAdi: String?){
        kategoriAdi?.let {
            filmKategorileri?.let {
                var kategoriFiltrele = it.filter { it.KategoriAdi!!.contains(kategoriAdi) }

                filmKategoriAdapter.setData(kategoriFiltrele)
                filmKategoriAdapter.notifyDataSetChanged()
            }
        }
    }

    fun arama(){
        binding.apply {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    adaGoreAra(newText)
                    return false
                }
            })
        }
    }



    override fun onBackPressed() {
        AlertDialogUtil.alertDialogShow(this@KategoriListesiActivity, resources.getString(R.string.uyari),
                resources.getString(R.string.cikmak_istediginize_eminmisiniz),
                resources.getString(R.string.evet),
                resources.getString(R.string.hayir),
                resources.getString(R.string.kategori_listes_activity))
    }

}