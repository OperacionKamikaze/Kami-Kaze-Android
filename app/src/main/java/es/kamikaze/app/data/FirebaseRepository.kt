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

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import es.kamikaze.app.core.FirebaseDatabaseApp
import es.kamikaze.app.data.model.User

class FirebaseRepository(val user: User) {

    private val database = FirebaseDatabaseApp.getDatabase()

    fun writeNewUser(user: User) {
        database.child(user.id).setValue(user)
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
}