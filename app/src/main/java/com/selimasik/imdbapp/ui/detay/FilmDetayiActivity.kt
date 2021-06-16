package com.selimasik.imdbapp.ui.detay

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.selimasik.imdbapp.R
import com.selimasik.imdbapp.databinding.ActivityFilmDetayiBinding
import com.selimasik.imdbapp.databinding.ActivityFilmLiseteleBinding
import com.selimasik.imdbapp.utils.Constants

class FilmDetayiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmDetayiBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetayiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init();
    }

    private fun init() {
        binding.txtFilmDetayAdi.text = Constants.filmAdi
        binding.txtFilmDetayAciklama.text = Constants.filmDetay
        binding.txtFilmDetayCikisTarihi.text = Constants.cikisTarihi
        binding.txtFilmDetayKategori.text = Constants.filmKategoriAdi
        binding.txtFilmDetayYildiz.text = Constants.filmYildizi

        val bytes = Base64.decode(Constants.filmImg, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        binding.imgFilmDetay.setImageBitmap(decodedImage)

    }
}