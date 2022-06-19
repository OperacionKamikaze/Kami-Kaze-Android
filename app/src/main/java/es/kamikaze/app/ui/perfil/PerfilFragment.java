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

package es.kamikaze.app.ui.perfil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import es.kamikaze.app.data.model.User;
import es.kamikaze.app.databinding.FragmentPerfilBinding;
import es.kamikaze.components.util.Extensions;
import es.kamikaze.components.util.KzStatsFieldDC;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding b;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KZViewModel kzViewModel = new ViewModelProvider(this).get(KZViewModel.class);
        b = FragmentPerfilBinding.inflate(inflater, container, false);

        kzViewModel.readUser();
        kzViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            b.kztvNombrePersonaje.setText(User.getInstancia().getUsername());
            b.kztvMonedas.setText(String.valueOf(User.getInstancia().getOro()));
            b.kztvNivel.setText(String.valueOf(User.getInstancia().getLvl()));
            b.pbVida.setMax(User.getInstancia().getLvl()*15);
            b.pbVida.setProgress(User.getInstancia().getPs());
            b.pbExperiencia.setMax(20);
            b.pbExperiencia.setProgress(User.getInstancia().getExp());
            b.kzsfAtaque.setData(new KzStatsFieldDC("Ataque", String.valueOf(User.getInstancia().getAt())));
            b.kzsfDefensa.setData(new KzStatsFieldDC("Defensa", String.valueOf(User.getInstancia().getDef())));
            b.kzsfVelocidad.setData(new KzStatsFieldDC("Velocidad", String.valueOf(User.getInstancia().getVel())));
            Extensions.bindData(b.imgAvatar, String.valueOf(User.getInstancia().getImg()));
        });

        b.kztvNombrePersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = new EditText(view.getContext());
                AlertDialog builder = new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle("Nuevo nombre de usuario")
                        .setMessage("Introduzca un nuevo nombre de usuario")
                        .setView(input)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(input.getText() != null) {
                                    User.getInstancia().setUsername(input.getText().toString());
                                    kzViewModel.editUser(User.getInstancia());
                                    dialogInterface.dismiss();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> {
                            dialog.dismiss();
                            System.exit(0);
                        }).create();

                builder.setCanceledOnTouchOutside(false);
                builder.setCancelable(false);
                builder.show();
            }
        });

        return b.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        b = null;
    }
}