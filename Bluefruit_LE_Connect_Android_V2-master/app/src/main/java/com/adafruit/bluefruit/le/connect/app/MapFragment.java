package com.adafruit.bluefruit.le.connect.app;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.content.Context;
import android.location.Criteria;
import com.google.android.gms.maps.model.CameraPosition;
import android.location.LocationManager;
import com.adafruit.bluefruit.le.connect.BuildConfig;
import com.adafruit.bluefruit.le.connect.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

 //   private GoogleMap mMap;
    int TAG_CODE_PERMISSION_LOCATION;
    public MapFragment() {
        // Required empty public constructor
    }
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       // setContentView(R.layout.fragment_map);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this::onMapReady);
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update ActionBar
        setActionBarTitle(R.string.map);

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment
    }

      @Override
      public void onMapReady(GoogleMap map) {
          googleMap = map;
          if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                  PackageManager.PERMISSION_GRANTED &&
                  ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                          PackageManager.PERMISSION_GRANTED) {
              googleMap.setMyLocationEnabled(true);
              googleMap.getUiSettings().setMyLocationButtonEnabled(true);
              LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
              Criteria criteria = new Criteria();
              googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
              Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
              if (location != null)
              {
                  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 19));
                  MainActivity.lat = location.getLatitude();
                  MainActivity.lon = location.getLongitude();
                  CameraPosition cameraPosition = new CameraPosition.Builder()
                          .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                          .zoom(19)                   // Sets the zoom
                          .bearing(0)                // Sets the orientation of the camera to east
                          .tilt(0)                   // Sets the tilt of the camera (degrees)
                          .build();                   // Creates a CameraPosition from the builder
                  googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              }
          }
              else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
    }
}


    // region Action Bar
    protected void setActionBarTitle(int titleStringId) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(titleStringId);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

}