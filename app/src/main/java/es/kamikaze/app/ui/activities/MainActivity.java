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

package es.kamikaze.app.ui.activities;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import es.kamikaze.app.R;
import es.kamikaze.app.core.Permisos;
import es.kamikaze.app.core.broadcast.InternetBroadcast;
import es.kamikaze.app.databinding.ActivityMainBinding;
import es.kamikaze.app.ui.map.MapFragment;
import es.kamikaze.app.ui.perfil.PerfilFragment;
import es.kamikaze.app.ui.social.SocialFragment;

public class MainActivity extends AppCompatActivity {

    InternetBroadcast broadcast = new InternetBroadcast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.bottomNavView.setBackground(null);
        b.bottomNavView.getMenu().getItem(1).setEnabled(false);

        b.fabMap.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(
                R.id.navHostFragmentContainer, new MapFragment()).commit());

        b.bottomNavView.setOnItemSelectedListener(item -> {
            Fragment temp = null;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_bolsa) {
                temp = new PerfilFragment();
            } else if (itemId == R.id.navigation_social) {
                temp = new SocialFragment();
            }
            if (temp != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragmentContainer, temp).commit();
            }
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        permisos();
    }

    private void permisos() {
        Permisos permisos = new Permisos(this, this);
        if (permisos.hasAllPerms(permisos.getListadoDePermisos())) {
            permisos.permissionsApp();
        }
    }

    @Override
    protected void onStart() {
        registerReceiver(broadcast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcast);
        super.onStop();
    }
}