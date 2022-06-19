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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
            b.kztvNombrePersonaje.setText(User.getInstanciaActual().getUsername());
            b.kztvMonedas.setText(String.valueOf(User.getInstanciaActual().getOro()));
            b.kztvNivel.setText(String.valueOf(User.getInstanciaActual().getLvl()));
            b.pbVida.setMax(User.getInstanciaActual().getLvl()*15);
            b.pbVida.setProgress(User.getInstanciaActual().getPs());
            b.pbExperiencia.setMax(20);
            b.pbExperiencia.setProgress(10);
            b.kzsfAtaque.setData(new KzStatsFieldDC("Ataque", String.valueOf(User.getInstanciaActual().getAt())));
            b.kzsfDefensa.setData(new KzStatsFieldDC("Defensa", String.valueOf(User.getInstanciaActual().getDef())));
            b.kzsfVelocidad.setData(new KzStatsFieldDC("Velocidad", String.valueOf(User.getInstanciaActual().getVel())));
            Extensions.bindData(b.imgAvatar, String.valueOf(User.getInstanciaActual().getImg()));
        });

        return b.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        b = null;
    }
}