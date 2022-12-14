package com.example.arroundme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
//import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.arroundme.databinding.ActivityNearbyPlacesOfCurrentLocationBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class NearbyPlacesOfCurrentLocation extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient client;
    LocationRequest request;
    private ActivityNearbyPlacesOfCurrentLocationBinding binding;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng tourEifeil = new LatLng(48.8584, 2.2945);
    LatLng sydney = new LatLng(47.6313,  -2.1016);
    LatLng MuseeLouvre = new LatLng(48.860294,2.338629);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNearbyPlacesOfCurrentLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        arrayList.add(tourEifeil);
        arrayList.add(sydney);
        arrayList.add(MuseeLouvre);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        client.connect();

        // Sets the map type to be "hybrid"
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        for(int i = 0; i<arrayList.size(); i++){

            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }
    }



//    private void getCurrentLocation() {
//        // Initialize task Location
//
//        Task<Location> task = fusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>(){
//
//            @Override
//            public void onSuccess(Location location) {
//                // When success
//                if(location != null){
//
//                    LatLng currentP = new LatLng(location.getLatitude(), location.getLongitude());
//
//                    mMap.addMarker(new MarkerOptions()
//                            .position(currentP)
//                            .title("Ma position"));
//
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentP));
//                }
//            }
//        });
//    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        if(location == null){

            Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();

        }else{

            LatLng latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.addMarker(new MarkerOptions()
                    .position(latLngCurrent)
                    .title("Ma position actuelle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent));

        }    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

            request = new LocationRequest().create();
            request.setInterval(1000);
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION
                            }, REQUEST_CODE);
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(client, request,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


//    private void getCurrentLocation(){
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions(this,new String[]
//                            {Manifest.permission.ACCESS_FINE_LOCATION
//                            }, REQUEST_CODE);
//            return;
//        }
//
//        Task<Location> task = fusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(@NonNull @org.jetbrains.annotations.NotNull Location location) {
//
//                if(location != null){
//
//                    currentLocation = location;
//                    Toast.makeText(getApplicationContext(), (int) currentLocation.getLatitude(), Toast.LENGTH_SHORT).show();
//                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                            .findFragmentById(R.id.map);
//                    mapFragment.getMapAsync(NearbyPlacesOfCurrentLocation.this);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (REQUEST_CODE){
//            case REQUEST_CODE:
//                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                    getCurrentLocation();
//                }
//                break;
//        }
//
//    }
}
