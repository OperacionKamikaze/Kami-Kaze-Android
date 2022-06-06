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

package es.kamikaze.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import es.kamikaze.app.databinding.ActivitySplashScreenBinding
import es.kamikaze.app.util.Permisos

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var b: ActivitySplashScreenBinding
    private var permisos: Permisos = null ?: Permisos(this, this)
    private val splashTimeOut: Long = 3000
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySplashScreenBinding.inflate(layoutInflater)
        mediaPlayer = MediaPlayer.create(this, R.raw.kz_splash_intro)
        mediaPlayer?.start()
        Thread.sleep(splashTimeOut)
        permisos = Permisos(this, this, b)
        iniciarPermisos()
    }

    override fun onPause() {
        super.onPause()
        if (!permisos.hasAllPerms())
            iniciarPermisos()
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }

    private fun iniciarPermisos() {
        val sPFirstStart: SharedPreferences = getSharedPreferences("sPFirstStart", Context.MODE_PRIVATE)
        val firstStart = sPFirstStart.getBoolean("firstStart", true)

        if (firstStart) {
            permisosPrimeraVez()
            return
        }

        // Si el usuario no ha aceptado los permisos, se le mostrará un mensaje de error
        if (permisos.hasAllPerms(*permisos.permisos)) {
            permisos.permissionsApp()
            return
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun permisosPrimeraVez() {
        val madb = MaterialAlertDialogBuilder(this)
            .setTitle("Primera vez en abrir la App")
            .setMessage(
                """
        Parece que es la primera vez que abres la aplicación.
        
        Necesita aceptar una serie de permisos para poder continuar, si no la aplicación no podrá hacer las operaciones correctamente.
        """.trimIndent()
            )
            .setPositiveButton("¡Vale!") { dialog: DialogInterface, _: Int ->
                if (permisos.hasAllPerms(*permisos.permisos)) {
                    permisos.askPermissions()
                } else {
                    Snackbar.make(b.root.rootView, "Tienes todos los permisos", Snackbar.LENGTH_SHORT).show()
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