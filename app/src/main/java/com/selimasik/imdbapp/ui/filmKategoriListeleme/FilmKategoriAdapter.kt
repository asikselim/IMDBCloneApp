package com.selimasik.imdbapp.ui.filmKategoriListeleme

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selimasik.imdbapp.data.ItemClickListener
import com.selimasik.imdbapp.data.model.FilmKategoriItem
import com.selimasik.imdbapp.databinding.CardviewKategorilistesiBinding
import com.selimasik.imdbapp.utils.Constants

class FilmKategoriAdapter(var filmKategorileri:ArrayList<FilmKategoriItem>, var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<FilmKategoriAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CardviewKategorilistesiBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewKategorilistesiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    fun setData(filtrelenenKategori: List<FilmKategoriItem>){
        filmKategorileri = filtrelenenKategori as ArrayList<FilmKategoriItem>
    }
    override fun getItemCount(): Int {
        return filmKategorileri.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                binding.txtKategoriAdi.text = filmKategorileri[position].KategoriAdi

                val bytes = Base64.decode(filmKategorileri[position].KategoriFotografi, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                binding.imgKategoriFotografi.setImageBitmap(decodedImage)



                cardviewFilmKategori.setOnClickListener{
                    Constants.filmKategoriAdi = filmKategorileri[position].KategoriAdi
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

}