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

package es.kamikaze.app.ui.bolsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.kamikaze.app.databinding.FragmentPerfilBinding;
import es.kamikaze.components.KzTextView;
import es.kamikaze.components.util.Extensions;

public class PerfilFragment extends Fragment {

    private PerfilViewModel bolsaViewModel;
    private FragmentPerfilBinding b;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bolsaViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        b = FragmentPerfilBinding.inflate(inflater, container, false);
        b.kztvNombrePersonaje.setText("John Cena");
        b.kztvMonedas.setText("ORO: 24500");
        b.kztvNivel.setText("LVL: 87");

//        bolsaViewModel.getText().observe(getViewLifecycleOwner(), b.kztvNombrePersonaje::setText);
        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Extensions.bindData(b.imgAvatar, "https://i.imgur.com/DvpvklR.png");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        b = null;
    }
}