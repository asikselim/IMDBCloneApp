package com.selimasik.imdbapp.data.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@Keep
@IgnoreExtraProperties
class FilmItem{

    @PropertyName("CikisTarihi")
    val CikisTarihi: String?=null

    @PropertyName("FilmAdi")
    val FilmAdi: String?=null

    @PropertyName("FilmDetay")
    val FilmDetay: String?=null

    @PropertyName("FilmKategorisi")
    val FilmKategorisi: String?=null

    @PropertyName("FilmYildizi")
    val FilmYildizi: String?=null

    @PropertyName("ImgFilm")
    val ImgFilm: String?=null


    override fun toString(): String {
        return "FilmItem(CikisTarihi=$CikisTarihi," +
                "FilmAdi=$FilmAdi," +
                "FilmDetay=$FilmDetay," +
                "FilmKategorisi=$FilmKategorisi," +
                "FilmYildizi=$FilmYildizi," +
                "ImgFilm=$ImgFilm)"
    }
}
