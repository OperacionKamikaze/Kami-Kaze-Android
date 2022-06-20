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

package es.kamikaze.app.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import es.kamikaze.app.R
import es.kamikaze.app.core.Permisos
import es.kamikaze.app.data.FirebaseRepository
import es.kamikaze.app.data.model.User
import es.kamikaze.app.databinding.ActivitySplashScreenBinding
import es.kamikaze.app.ui.tutorial.TutorialActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var b: ActivitySplashScreenBinding
    private val splashTimeOut: Long = 3000
    private var mediaPlayer: MediaPlayer? = null
    private val permisos = Permisos(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySplashScreenBinding.inflate(layoutInflater)

        mediaPlayer = MediaPlayer.create(this, R.raw.kz_splash_intro)
        mediaPlayer?.start()
        Thread.sleep(splashTimeOut)

        if (permisos.hasAllPerms(*permisos.listadoDePermisos)) {
            iniciarPermisos()
        } else {
            usuarioLogueado()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permisos.codPermisos -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    usuarioLogueado()
                } else {
                    iniciarPermisos()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }

    private fun usuarioLogueado() {
        val prefsUser: SharedPreferences = getSharedPreferences("userCreate", Context.MODE_PRIVATE)
        val user = prefsUser.getString("singletonUser", "")
        if (user == "") {
            startActivity(Intent(this, TutorialActivity::class.java))
            finish()
        } else {
            User.setInstancia(Gson().fromJson(user, User::class.java))
            val repositorio = FirebaseRepository();
            repositorio.readUser(User.getInstancia().id)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun iniciarPermisos() {
        val sPFirstStart: SharedPreferences = getSharedPreferences("sPFirstStart", Context.MODE_PRIVATE)
        val firstStart = sPFirstStart.getBoolean("firstStart", true)

        if (firstStart) {
            permisosPrimeraVez()
            return
        }

        // Si el usuario no ha aceptado los permisos, se le mostrará un mensaje de error
        if (permisos.hasAllPerms(*permisos.listadoDePermisos)) {
            permisos.permissionsApp()
            return
        }
    }

    private fun permisosPrimeraVez() {
        val madb = MaterialAlertDialogBuilder(this)
            .setTitle("¡Vaya!, parece que eres nuevo...")
            .setMessage(
                """
        Acabas de instalar la aplicación. Bienvenido.
        Se necesitan aceptar una serie de permisos para poder continuar, si no la aplicación no podrá hacer sus 
        operaciones correctamente.
        """.trimIndent()
            )
            .setPositiveButton("¡Vale!") { dialog: DialogInterface, _: Int ->
                if (permisos.hasAllPerms(*permisos.listadoDePermisos)) {
                    permisos.askPermissions()
                } else {
                    Toast.makeText(this, "Tienes todos los permisos", Toast.LENGTH_LONG).show()
                }
                dialog.dismiss()
            }.setNegativeButton("Cancelar") { dialog: DialogInterface, _: Int -> dialog.cancel() }
            .create()

        madb.setCancelable(false)
        madb.setCanceledOnTouchOutside(false)
        madb.show()

        val prefs: SharedPreferences = getSharedPreferences("sPFirstStart", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }
}