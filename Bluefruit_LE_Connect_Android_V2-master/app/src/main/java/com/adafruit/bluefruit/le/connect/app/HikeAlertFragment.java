package com.adafruit.bluefruit.le.connect.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adafruit.bluefruit.le.connect.BuildConfig;
import com.adafruit.bluefruit.le.connect.R;

import android.widget.Button;

public class HikeAlertFragment extends Fragment  {
    public static HikeAlertFragment newInstance() {
        return new HikeAlertFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hikealert, container, false);
    }

//
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView versionTextView = view.findViewById(R.id.versionTextView);
        if (versionTextView != null) {
            versionTextView.setText(String.format("v%s", BuildConfig.VERSION_NAME));
        }

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();        // update options menu with current values
        }

        final Button button = view.findViewById(R.id.about_button);
        button.setOnClickListener(this::goToAboutPage);
        final Button button2 = view.findViewById(R.id.MapOverview);
        button2.setOnClickListener(this::goToMap);
        final Button button3 = view.findViewById(R.id.emergency);
        button3.setOnClickListener(this::goToEmergencyOptions);

    }

    //On click bs
    public void goToAboutPage(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            AboutPage fragment = AboutPage.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public void goToMap(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            MapFragment fragment = MapFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public void goToEmergencyOptions(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            emergencyOptions fragment = emergencyOptions.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}