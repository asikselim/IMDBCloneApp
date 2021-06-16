package com.selimasik.imdbapp.data.repository

import com.selimasik.imdbapp.data.remote.FilmKategoriDataSource
import com.selimasik.imdbapp.data.firebase.FilmKategoriFirebaseDataSource
import com.selimasik.imdbapp.data.model.FilmKategoriItem
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class FilmKategoriRepository {
    private var filmKategoriDataSource: FilmKategoriDataSource?=null

    init {
        filmKategoriDataSource= FilmKategoriFirebaseDataSource()
    }

    fun filmKategorileriGetir(): Flow<Resource<List<FilmKategoriItem>>>
    {
        return filmKategoriDataSource!!.filmKategorileriGetir()
    }

}