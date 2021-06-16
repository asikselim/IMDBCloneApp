package com.selimasik.imdbapp.ui.filmListeleme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.data.repository.FilmRepository
import com.selimasik.imdbapp.utils.ResourceStatus
import kotlinx.coroutines.launch

class FilmViewModel :ViewModel() {
    private  val filmRepository: FilmRepository = FilmRepository()

    init {
        filmGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var filmLiveData = MutableLiveData<List<FilmItem>>()
    var error =    MutableLiveData<Throwable>()


    fun filmGetir()  = viewModelScope.launch {

        filmRepository.filmGetir()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            filmLiveData.postValue(it.data!!)
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