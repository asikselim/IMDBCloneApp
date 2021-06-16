package com.selimasik.imdbapp.ui.filmKategoriListeleme

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.selimasik.imdbapp.data.model.FilmKategoriItem

import com.selimasik.imdbapp.data.repository.FilmKategoriRepository
import com.selimasik.imdbapp.utils.ResourceStatus
import kotlinx.coroutines.launch

class FilmKategoriViewModel : ViewModel() {

    private  val filmKategoriRepository: FilmKategoriRepository = FilmKategoriRepository()

    init {
        filmKategorileriGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var filmKategorilerLiveData = MutableLiveData<List<FilmKategoriItem>>()
    var error =    MutableLiveData<Throwable>()


    fun filmKategorileriGetir()  = viewModelScope.launch {

        filmKategoriRepository.filmKategorileriGetir()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {

                            filmKategorilerLiveData.postValue(it.data!!)
                            loading?.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            error.postValue(it.throwable!!)
                            loading?.postValue(false)
                        }
                    }
                }
    }
}

