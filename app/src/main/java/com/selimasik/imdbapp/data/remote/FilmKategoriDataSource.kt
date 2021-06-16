package com.selimasik.imdbapp.data.remote


import com.selimasik.imdbapp.data.model.FilmKategoriItem
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmKategoriDataSource {
    fun filmKategorileriGetir(): Flow<Resource<List<FilmKategoriItem>>>
}