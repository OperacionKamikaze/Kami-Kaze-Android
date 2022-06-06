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

package es.kamikaze.app.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import es.kamikaze.app.MainActivity
import kotlin.system.exitProcess

class Permisos(private val context: Context, private val activity: Activity, private val b: ViewBinding? = null) {

    /**
     * Pedimos todos nuestros permisos.
     */
    val permisos = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA
    )

    private val codPermisos = 1

    /**
     * Método para ver los permisos en forma de bucle (Método, más corto existente), noi hace falta
     * uso de onRequestPermissionResult. Solo se pedirán los permisos que están en el archivo de
     * manifiesto y son completamente requeridos por nuestra aplicación.
     *
     * @param permissions Permisos pasados por un array propio de permisos.
     *
     * @return Los permisos garantizados.
     */
    fun hasAllPerms(vararg permissions: String?): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission!!) != PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }

    /**
     * Método muy parecido a [.hasAllPerms] solo que en este contamos los
     * permisos que son denegados por el usuario o no los tiene activados aún.
     *
     * @param permissions Permisos pasados por un array propio de permisos.
     *
     * @return Número de permisos denegados.
     */
    fun permissionCount(vararg permissions: String?): Int {
        var permissionCount = 0
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission!!) == PackageManager.PERMISSION_DENIED) {
                permissionCount++
            }
        }
        return permissionCount
    }

    /**
     * Método para explicar los permisos necesarios para ejecutar la aplicación 1 a 1.
     *
     * @param permissions Permisos pasados por un array propio de permisos.
     */
    private fun explainPermission(vararg permissions: String) {
        val perms = StringBuilder()
        for (permission in permissions) {
            when (permission) {
                Manifest.permission.ACCESS_FINE_LOCATION -> if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    perms.append("- Permiso de geolocalización\n")
                }
                Manifest.permission.ACCESS_COARSE_LOCATION -> if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    perms.append("- Permiso de localización (internet)\n")
                }
                Manifest.permission.CAMERA -> if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    perms.append("- Permiso de acceso a la cámara\n")
                }
            }
        }

        val builder = MaterialAlertDialogBuilder(context)
            .setTitle("Se necesitan " + permissionCount(*permissions) + " permisos...")
            .setMessage("Se necesitan los siguiente permisos para un funcionamiento correcto de la App:\n\n$perms")
            .setPositiveButton(
                "Ok"
            ) { _: DialogInterface?, _: Int ->
                ActivityCompat.requestPermissions(
                    activity,
                    permissions,
                    codPermisos
                )
            }.setNegativeButton("Cancelar") { _: DialogInterface?, _: Int ->
                exitProcess(0)
            }
            .create()

        builder.setCancelable(false)
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    /**
     * Método público general para pedir los permisos.
     */
    fun askPermissions() {
        if (hasAllPerms(*permisos)) {
            explainPermission(*permisos)
        }
    }

    /**
     * Método estático para el diálogo cuando no se tienen permisos.
     */
    fun permissionsApp() {
        val alertDialog = MaterialAlertDialogBuilder(context)
            .setTitle("Necesitamos sus ¡permisos!")
            .setMessage(
                "La aplicación necesita una serie de permisos para poder continuar. Si se facilitan estos permisos, " +
                        "la aplicación se cerrará ya que no tiene los permisos suficientes."
            )
            .setPositiveButton("¡Perfecto!") { dialog: DialogInterface, _: Int ->
                if (hasAllPerms(*permisos)) {
                    askPermissions()
                } else {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                }
                dialog.dismiss()
            }.setNegativeButton("No, salir") { _: DialogInterface?, _: Int ->
                exitProcess(0)
            }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}