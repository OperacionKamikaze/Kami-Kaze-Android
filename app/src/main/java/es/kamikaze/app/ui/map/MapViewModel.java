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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.kamikaze.app.MainActivity;
import es.kamikaze.app.R;
import es.kamikaze.app.databinding.FragmentMapBinding;

public class MapViewModel extends ViewModel {

    //private MutableLiveData<String> mText;
    //private FragmentMapBinding binding;


    public MapViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }
    /*public LiveData<String> getText() {
        return mText;
    }*/


}