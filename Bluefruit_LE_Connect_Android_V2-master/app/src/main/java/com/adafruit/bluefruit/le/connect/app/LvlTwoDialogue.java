package com.adafruit.bluefruit.le.connect.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adafruit.bluefruit.le.connect.BuildConfig;
import com.adafruit.bluefruit.le.connect.R;

import android.widget.Button;

public class LvlTwoDialogue extends Fragment  {
    UartAdapter uart = new UartAdapter();
    public static LvlTwoDialogue newInstance() {
        return new LvlTwoDialogue();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_lvl_two_dialogue, container, false);
    }

    //
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update ActionBar
        setActionBarTitle(R.string.L1);

        TextView versionTextView = view.findViewById(R.id.versionTextView);
        if (versionTextView != null) {
            versionTextView.setText(String.format("v%s", BuildConfig.VERSION_NAME));
        }

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();        // update options menu with current values
        }

        final Button button = view.findViewById(R.id.no_button);
        button.setOnClickListener(this::goToHikeAlert);
        final Button button2 = view.findViewById(R.id.yes_button);
        button2.setOnClickListener(this::goToConfirmPage);
//        final Button button3 = view.findViewById(R.id.emergency);
//        button3.setOnClickListener(this::goToAboutPage);

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
    //On click bs
    public void goToHikeAlert(View view) {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            //Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            // Log.i("MainActivity", "nothing on backstack, calling super");
        }
    }
    public void goToConfirmPage(View view) {
        uart.setupUart(getContext());
//        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
//                PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
//                        PackageManager.PERMISSION_GRANTED) {
//            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//            Criteria criteria = new Criteria();
//            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
//            int i = 0;
//            while(location == null){
//                location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
//                if(i > 100){break;}
//                i++;
//            }
//            if(location != null) {
//                MainActivity.lat = location.getLatitude();
//                MainActivity.lon = location.getLongitude();
//            }
//        }
////        Alert, concat'd id, deviceid, severity, time sent, latitude, longitude
////        #AT,ID,0,DI,0,SV,0,ST,0,TS,0,LT,0,LN,0~
//        String message1 = "#AT,ID,12349,DI,9~\n";
//        String message2 = "#AT,ST,0~\n";
//        String message3 = "#AT,LT,"+MainActivity.lat+"~\n";
//        String message4 = "#AT,LN,"+MainActivity.lon+"~\n";
//        uart.send(message1);
//        try {
//            //set time in mili
//            Thread.sleep(500);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        uart.send(message2);
//        try {
//            //set time in mili
//            Thread.sleep(500);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        uart.send(message3);
//        try {
//            //set time in mili
//            Thread.sleep(500);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        uart.send(message4);
        uart.send("A\n");
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            ConfirmPage fragment = ConfirmPage.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
//    public void goToEmergencyOptions(View view) {
//        //FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentActivity activity = getActivity();
//        FragmentManager fragmentManager = activity.getSupportFragmentManager();
//        if (fragmentManager != null) {
//            AboutPage fragment = AboutPage.newInstance();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                    .replace(R.id.contentLayout, fragment, "About");
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        }
//    }
}