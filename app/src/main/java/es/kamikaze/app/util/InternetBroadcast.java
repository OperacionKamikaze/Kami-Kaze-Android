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

package es.kamikaze.app.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class InternetBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!InternetConexion.estaConectadoInternet(context)) {
            AlertDialog builder = new MaterialAlertDialogBuilder(context)
                    .setTitle("¡No tienes conexión a internet!")
                    .setMessage("Se necesita conexión a internet para poder seguir usando la aplicación.")
                    .setPositiveButton("Reintentar", (dialog, which) -> {
                        dialog.dismiss();
                        onReceive(context, intent);
                    }).setNegativeButton("Salir", (dialog, which) -> {
                        dialog.dismiss();
                        System.exit(0);
                    }).create();

            builder.setCanceledOnTouchOutside(false);
            builder.setCancelable(false);
            builder.show();
        }
    }
}