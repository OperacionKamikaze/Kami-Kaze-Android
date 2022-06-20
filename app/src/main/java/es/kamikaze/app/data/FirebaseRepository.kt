/*
 * Copyright (c) 2022. Operación Kami - Kaze.
 *
 * Licensed under the GNU General Public License v3.0
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Permissions of this strong copyleft license are conditioned on making available complete
 * source code of licensed works and modifications, which include larger works using a licensed
 * work, under the same license. Copyright and license notices must be preserved. Contributors
 * provide an express grant of patent rights.
 */

package es.kamikaze.app.data

import android.content.ContentValues.TAG
import android.media.Image
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import es.kamikaze.app.core.FirebaseDatabaseApp
import es.kamikaze.app.data.model.User

class FirebaseRepository() {

    private val database = FirebaseDatabaseApp.getDatabase()

    fun writeUser(user: User) {
        database.child(user.id).setValue(user)
    }


    fun readUser(_user: MutableLiveData<User>) {
        database.child(User.getInstancia().id).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                _user.value = user
                User.setInstancia(_user.value)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.v("XYZ loadPost:onCancelled", "${databaseError.toException()}")
            }
        })
    }

    fun readUser( id : String ) : User? {
        var usuario : User? = null
        database.child(id).addValueEventListener(object :
            ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                usuario = user
                User.setInstancia(usuario)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.v("XYZ loadPost:onCancelled", "${databaseError.toException()}")
            }
        })

        return usuario
    }



    @IgnoreExtraProperties
    data class Post(

        var at: Int = 0,
        var def: Int = 0,
        var exp: Int = 0,
        var id:String = "",
        var img: String = "",
        var level: Int = 0,
        var lvl: Int = 0,
        var oro: Int = 0,
        var ps: Int = 0,
        var username: String = "",
        var vel: Int = 0,
    ) {

        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(

                "at" to at,
                "def" to def,
                "exp" to exp,
                "id" to id,
                "img" to img,
                "level" to level,
                "lvl" to lvl,
                "oro" to oro,
                "ps" to ps,
                "username" to username,
                "vel" to vel,
            )
        }
    }
}
