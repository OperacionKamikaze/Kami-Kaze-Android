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

import android.app.Dialog;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import es.kamikaze.app.R;
import es.kamikaze.app.core.Permisos;
import es.kamikaze.app.core.broadcast.InternetBroadcast;
import es.kamikaze.app.data.FirebaseRepository;
import es.kamikaze.app.data.model.User;
import es.kamikaze.app.databinding.ActivityMainBinding;
import es.kamikaze.app.ui.map.MapFragment;
import es.kamikaze.app.ui.perfil.PerfilFragment;
import es.kamikaze.app.ui.social.SocialFragment;

public class MainActivity extends AppCompatActivity {

    InternetBroadcast broadcast = new InternetBroadcast();
    static Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permisos();
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.getMenu().getItem(1).setEnabled(false);

        MapFragment mapFragment = new MapFragment();
        SocialFragment socialFragment = new SocialFragment();
        PerfilFragment bolsaFragment = new PerfilFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragmentContainer, mapFragment).commit();
        binding.bottomNavView.getMenu().getItem(1).setChecked(true);

        binding.fabMap.setOnClickListener(v -> {
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.navHostFragmentContainer, mapFragment
                    ).commit();

                    binding.bottomNavView.getMenu().getItem(1).setChecked(true);
                }
        );

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            Fragment temp = null;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_bolsa) {
                temp = bolsaFragment;
            } else if (itemId == R.id.navigation_social) {
                temp = socialFragment;

                User.getInstancia().putExperience(22);

            }
            if (temp != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragmentContainer, temp).commit();
            }
            return true;
        });


        myDialog = new Dialog(this);

    }


    public static void popupLevel() {
        //ventana emergente

        LinearLayout lyAt, lyDef, lyVel;

        User usuario = User.getInstancia();
        FirebaseRepository repositorio = new FirebaseRepository();
        myDialog.setContentView(R.layout.popup_levelup);


        TextView tvPotenciar = myDialog.findViewById(R.id.tvPotenciar);
        String s = "Elige una estadística a potenciar con " + usuario.getLvl() + " puntos";
        tvPotenciar.setText(s);


        TextView tvAt = myDialog.findViewById(R.id.tvAtaque);
        tvAt.setText(String.valueOf(usuario.getAt()));

        TextView tvVel = myDialog.findViewById(R.id.tvVel);
        tvVel.setText( String.valueOf(usuario.getVel()));

        TextView tvDef = myDialog.findViewById(R.id.tvDef);
        tvDef.setText(String.valueOf(usuario.getDef()));




        lyAt = (LinearLayout) myDialog.findViewById(R.id.ataque);
        lyAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                usuario.setAt(usuario.getAt() + usuario.getLvl());
                repositorio.writeUser(usuario);

            }
        });


        lyVel = (LinearLayout) myDialog.findViewById(R.id.velocidad);
        lyVel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                usuario.setVel(usuario.getVel() + usuario.getLvl());
                repositorio.writeUser(usuario);
            }
        });

        lyDef = (LinearLayout) myDialog.findViewById(R.id.defensa);
        lyDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                usuario.setDef(usuario.getDef() + usuario.getLvl());
                repositorio.writeUser(usuario);
            }
        });

        myDialog.setCanceledOnTouchOutside(false);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

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