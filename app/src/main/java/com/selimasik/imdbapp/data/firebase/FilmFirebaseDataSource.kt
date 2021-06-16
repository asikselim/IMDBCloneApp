package com.selimasik.imdbapp.data.firebase

import com.google.firebase.database.*
import com.selimasik.imdbapp.data.model.FilmItem
import com.selimasik.imdbapp.data.remote.FilmDataSource
import com.selimasik.imdbapp.utils.Constants
import com.selimasik.imdbapp.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FilmFirebaseDataSource : FilmDataSource {

    override  fun filmGetir(): Flow<Resource<List<FilmItem>>> = callbackFlow {
        try {

            offer(Resource.Loading())

            val myRef: Query = FirebaseDatabase.getInstance().getReference("Filmler").orderByChild("FilmKategorisi").equalTo(Constants.filmKategoriAdi)

            var filmListesi = arrayListOf<FilmItem>()


            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {


                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(FilmItem::class.java)!!
                        filmListesi.add(item)


                    }



                    offer(Resource.Success(filmListesi))
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