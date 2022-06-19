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

package es.kamikaze.app.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.kamikaze.app.data.FirebaseRepository
import es.kamikaze.app.data.model.User
import kotlinx.coroutines.launch

class KZViewModel : ViewModel() {

    private val repository = FirebaseRepository(User.getInstancia())
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.writeNewUser(user)
        }
    }

    fun editUser (user: User) {
        viewModelScope.launch {
            repository.writeNewPost(user)
        }
    }

    fun readUser() {
        viewModelScope.launch {
            repository.readUser(_user)
        }
    }
}