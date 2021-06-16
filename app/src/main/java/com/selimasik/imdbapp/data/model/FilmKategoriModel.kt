package com.selimasik.imdbapp.data.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

@Keep
@IgnoreExtraProperties
class FilmKategoriItem{

    @PropertyName("KategoriFotografi")
    val KategoriFotografi: String?=null

    @PropertyName("KategoriAdi")
    val KategoriAdi: String?=null


    override fun toString(): String {
        return "FilmKategoriItem(KategoriFotografi=$KategoriFotografi, KategoriAdi=$KategoriAdi)"
    }
}

