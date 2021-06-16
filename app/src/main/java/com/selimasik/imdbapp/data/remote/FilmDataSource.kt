package com.selimasik.imdbapp.data.remote

import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmDataSource {
    fun filmGetir(): Flow<Resource<List<FilmItem>>>
}