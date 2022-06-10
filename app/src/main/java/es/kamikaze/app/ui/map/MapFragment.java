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

package es.kamikaze.app.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import es.kamikaze.app.databinding.FragmentMapBinding;
import es.kamikaze.app.ui.activities.MainActivity;
import es.kamikaze.app.R;

public class MapFragment extends Fragment implements GoogleMap.OnCameraIdleListener, LocationListener, OnEnemySpawnListener {

    public static GoogleMap mapa;
    private MapViewModel mapViewModel;
    private FragmentMapBinding binding;
    private MainActivity main;
    private LocationManager locationManager;
    private static Location localizacion;
    private static Handler mainHandler;


    /* MAPA Y SUS METODOS */
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            //guardamos instancia del mapa
            mapa = googleMap;

            //limites de zoom
            mapa.setMinZoomPreference(20.0f);
            mapa.setMaxZoomPreference(25.0f);

            if (main != null) {
                //Comprobamos permiso y activamos localizacion
                if (ActivityCompat.checkSelfPermission(main.getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(main.getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }


                mapa.setMyLocationEnabled(true);
                locationManager = (LocationManager) main.getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 100, MapFragment.this);
            }

            mapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    marker.remove();
                    Toast.makeText(MapFragment.this.getContext(), "UNITY AL ATAKE", Toast.LENGTH_LONG).show();





                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    marker.remove();
                    return false;
                }
            });

            //hacemos que lacamara enfoque a nuestra localizacion
            /*CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(mapa.getMyLocation().getLatitude(),mapa.getMyLocation().getLatitude()))     // Sets the center of the map
                    .zoom(25.0f)                  // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                 // Creates a CameraPosition from the builder
            //mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

            //crearMarcador(new LatLng(37.1519, -3.6231));

            //LatLng sydney = new LatLng(-34, 151);
            //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            //mapa.moveCamera(CameraUpdateFactory.newLatLng(sydney));








            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(googleMap.getCameraPosition().target)     // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                  // Creates a CameraPosition from the builder
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

           /* // Turn on the My Location layer and the related control on the map.
            updateLocationUI();

            // Get the current location of the device and set the position of the map.
            getDeviceLocation();


            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.getResult();
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        }
                    }
                }
            });*/

        }
    };

    @Override
    public void onCameraIdle() {
        Toast.makeText(main.getBaseContext(), mapa.getCameraPosition().toString(), Toast.LENGTH_SHORT).show();
    }


    public void crearMarcador(LatLng position) {
        //En nuestra app pueden haber varios tipos de criaturas que aparecerán
        mapa.addMarker(new MarkerOptions().position(position).title("Enemigo").icon(BitmapFromVector(main.getBaseContext())));


    }

    //metodo usado para cambiar la imagen de los marcadores
    private BitmapDescriptor BitmapFromVector(Context context) {
        int vectorResId;
        Random rand = new Random();
        int numero = rand.nextInt(5);
        if (numero < 1) {
            vectorResId = R.drawable.ico_monster4;
        }else if (numero < 2) {
            vectorResId = R.drawable.ico_monster5;
        }else if (numero < 3) {
            vectorResId = R.drawable.ico_monster6;
        }else if (numero < 4) {
            vectorResId = R.drawable.ico_monster7;
        }else{
            vectorResId = R.drawable.ico_monster4;
        }

        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))     // Sets the center of the map to Mountain View
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                  // Creates a CameraPosition from the builder
        mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mapViewModel.startEnemies(this);
        localizacion = location;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);



        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Get a handler that can be used to post to the main thread
        mainHandler = new Handler(this.requireContext().getMainLooper());

        mapViewModel.startEnemies(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main = (MainActivity) this.getActivity();
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.navHostFragmentContainer);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapViewModel.pause();
        binding = null;
    }

    @Override
    public void enemySpawn() {
        Random rand = new Random(); //instance of random class
        if (localizacion != null){

            Double lat = localizacion.getLatitude() + (Double) ((rand.nextDouble() * 5.0) - 2.5 ) / 10000;
            Double lng = localizacion.getLongitude() + (Double) ((rand.nextDouble() * 5.0) - 2.5 ) / 10000;
            MarkerOptions enemigoOptions = new MarkerOptions()
                    .position(new LatLng(
                            lat,
                            lng
                    ))
                    .title("Enemigo").icon(BitmapFromVector(main.getBaseContext()));



        //onClickListener

        //mapa.addMarker(enemigo);


        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Marker enemigo;
                enemigo = mapa.addMarker( enemigoOptions );
                mapViewModel.addEnemigo(enemigo);


            } // This is your code
        };
        mainHandler.post(myRunnable);

        }
    }

    @Override
    public void enemyDelete(Marker enemy) {


        //Handler mainHandler = new Handler(requireContext().getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                enemy.remove();
            } // This is your code
        };
        mainHandler.post(myRunnable);
    }
}