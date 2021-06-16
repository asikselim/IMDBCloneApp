package com.selimasik.imdbapp.data

interface ItemClickListener {
    fun onDelete(position: Int)
    fun onItemClick(position: Int)
}