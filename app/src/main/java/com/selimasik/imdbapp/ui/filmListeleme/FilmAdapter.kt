package com.selimasik.imdbapp.ui.filmListeleme

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selimasik.imdbapp.data.ItemClickListener
import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.databinding.CardviewFilmlisteleBinding
import com.selimasik.imdbapp.utils.Constants

class FilmAdapter(var filmler:ArrayList<FilmItem>, var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CardviewFilmlisteleBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewFilmlisteleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return filmler.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {

                binding.txtFilmAdi.text = filmler[position].FilmAdi
                binding.txtCikisTarihi.text = filmler[position].CikisTarihi
                binding.txtFilmYildiz.text = filmler[position].FilmYildizi

                val bytes = Base64.decode(filmler[position].ImgFilm, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                binding.imgFilmFotograf.setImageBitmap(decodedImage)



                binding.cardviewFilmListele.setOnClickListener{

                    Constants.filmKategoriAdi = filmler[position].FilmKategorisi
                    Constants.cikisTarihi = filmler[position].CikisTarihi
                    Constants.filmDetay = filmler[position].FilmDetay
                    Constants.filmYildizi = filmler[position].FilmYildizi
                    Constants.filmAdi = filmler[position].FilmAdi
                    Constants.filmImg = filmler[position].ImgFilm


                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}