package com.adafruit.bluefruit.le.connect.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adafruit.bluefruit.le.connect.BuildConfig;
import com.adafruit.bluefruit.le.connect.R;

import android.widget.Button;

public class emergencyOptions extends Fragment  {
    public static emergencyOptions newInstance() {
        return new emergencyOptions();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.emergency_options, container, false);
    }

    //
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update ActionBar
        setActionBarTitle(R.string.emergency_options);

        TextView versionTextView = view.findViewById(R.id.versionTextView);
        if (versionTextView != null) {
            versionTextView.setText(String.format("v%s", BuildConfig.VERSION_NAME));
        }

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();        // update options menu with current values
        }

        final Button button = view.findViewById(R.id.Main_Menu);
        button.setOnClickListener(this::goToHikeAlert);
        final Button button2 = view.findViewById(R.id.Level_One);
        button2.setOnClickListener(this::goToL1);
        final Button button3 = view.findViewById(R.id.Level_Two);
        button3.setOnClickListener(this::goToL2);
        final Button button4 = view.findViewById(R.id.Level_Three);
        button4.setOnClickListener(this::goToL3);

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
    public void goToL1(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            LvlOneDialogue fragment = LvlOneDialogue.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public void goToL2(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            LvlTwoDialogue fragment = LvlTwoDialogue.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public void goToL3(View view) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            LvlThreeDialogue fragment = LvlThreeDialogue.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.contentLayout, fragment, "About");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}