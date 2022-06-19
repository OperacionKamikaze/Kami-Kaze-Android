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
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import es.kamikaze.app.core.FirebaseDatabaseApp
import es.kamikaze.app.data.model.User

class FirebaseRepository(val user: User) {

    private val database = FirebaseDatabaseApp.getDatabase()

    fun writeNewUser(user: User) {
        database.child(user.id).setValue(user)
    }

    fun writeNewPost(user: User) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val userId = user.id
        val key = database.child(userId).push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }

        val post = Post(
            User.getInstancia().oro,
            User.getInstancia().at,
            User.getInstancia().def,
            User.getInstancia().vel,
            User.getInstancia().ps,
            User.getInstancia().exp,
            User.getInstancia().lvl,
            User.getInstancia().username,
        )
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/posts/$key" to postValues,
            "/user-posts/$userId/$key" to postValues
        )

        database.updateChildren(childUpdates)
    }

    fun readUser(_user: MutableLiveData<User>) {
        database.child(user.id).addValueEventListener(object :
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

    @IgnoreExtraProperties
    data class Post(
        var oro: Int = 0,
        var at: Int = 0,
        var def: Int = 0,
        var vel: Int = 0,
        var ps: Int = 0,
        var exp: Int = 0,
        var lvl: Int = 0,
        var username: String = ""
    ) {

        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "oro" to oro,
                "at" to at,
                "def" to def,
                "vel" to vel,
                "ps" to ps,
                "exp" to exp,
                "lvl" to lvl,
                "username" to username
            )
        }
    }
}