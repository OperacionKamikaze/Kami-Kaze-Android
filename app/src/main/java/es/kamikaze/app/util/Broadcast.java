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

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import es.kamikaze.app.R;

public class Broadcast extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if(!conexion.estaConectadoInternet(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.sin_internet_layout, null);
            builder.setView(layout_dialog);

            AppCompatButton btnReintentar = layout_dialog.findViewById(R.id.btnReintentar);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnReintentar.setOnClickListener(view -> {
                dialog.dismiss();
                onReceive(context, intent);
            });
        }
    }
}
