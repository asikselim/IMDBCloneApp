package com.selimasik.imdbapp.data.firebase

import com.google.firebase.database.*
import com.selimasik.imdbapp.data.model.FilmKategoriItem
import com.selimasik.imdbapp.data.remote.FilmKategoriDataSource
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FilmKategoriFirebaseDataSource: FilmKategoriDataSource {


    override  fun filmKategorileriGetir(): Flow<Resource<List<FilmKategoriItem>>> = callbackFlow {
        try {

            offer(Resource.Loading())

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
            val myRef: DatabaseReference = database.child("FilmKategorileri")

            var filmKategoriListesi = arrayListOf<FilmKategoriItem>()


            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {


                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(FilmKategoriItem::class.java)!!
                        filmKategoriListesi.add(item)


                    }



                    offer(Resource.Success(filmKategoriListesi))
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            awaitClose {  }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }
    }


}
