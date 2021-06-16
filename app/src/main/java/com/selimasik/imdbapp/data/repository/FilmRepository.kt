package com.selimasik.imdbapp.data.repository

import com.selimasik.imdbapp.data.remote.FilmDataSource
import com.selimasik.imdbapp.data.firebase.FilmFirebaseDataSource
import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class FilmRepository {
    private var filmDataSource: FilmDataSource?=null

    init {
        filmDataSource= FilmFirebaseDataSource()
    }

    fun filmGetir(): Flow<Resource<List<FilmItem>>>
    {
        return filmDataSource!!.filmGetir()
    }
}