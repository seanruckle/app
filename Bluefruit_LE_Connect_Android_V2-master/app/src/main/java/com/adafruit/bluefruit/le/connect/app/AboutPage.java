package com.adafruit.bluefruit.le.connect.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adafruit.bluefruit.le.connect.BuildConfig;
import com.adafruit.bluefruit.le.connect.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutPage extends Fragment {
    public AboutPage() {
        // Required empty public constructor
    }
    public static AboutPage newInstance() {
        AboutPage fragment = new AboutPage();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public void goToMainMenu (View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        onBackPressed();
//    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update ActionBar
        setActionBarTitle(R.string.about_title);

        TextView versionTextView = view.findViewById(R.id.versionTextView);
        if (versionTextView != null) {
            versionTextView.setText(String.format("v%s", BuildConfig.VERSION_NAME));
        }

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();        // update options menu with current values
        }
        final Button button = view.findViewById(R.id.button_Back2MP);
        button.setOnClickListener(this::goToHikeAlert);
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

    public void goToHikeAlert(View view) {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            //Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            // Log.i("MainActivity", "nothing on backstack, calling super");
        }
    }
}
