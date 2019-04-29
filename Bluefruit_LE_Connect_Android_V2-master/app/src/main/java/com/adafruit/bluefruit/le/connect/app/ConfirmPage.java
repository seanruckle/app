//package com.adafruit.bluefruit.le.connect.app;
//

//
//import com.adafruit.bluefruit.le.connect.BuildConfig;
//import com.adafruit.bluefruit.le.connect.R;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//public class ConfirmPage extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirm_page);
//
//            Calendar c = Calendar.getInstance();
//            System.out.println("Current time => "+c.getTime());
//
//            SimpleDateFormat myTime = new SimpleDateFormat("HH:mm:ss");
//            SimpleDateFormat myDate = new SimpleDateFormat("MM/dd/yyyy");
//            String formattedTime = myTime.format(c.getTime());
//            String formattedDate = myDate.format(c.getTime());
//
//            //Toast.makeText(this, "This page will close automatically", Toast.LENGTH_LONG).show();
//            final Toast toast = Toast.makeText(this, "This page will close automatically", Toast.LENGTH_LONG);
//
//            TextView txtView = (TextView) findViewById(R.id.textView4);
//            txtView.setText("Current Time:\n"+formattedTime + "\n\nCurrent Date:\n" + formattedDate);
//            txtView.setTextSize(40);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                toast.show();
//            }
//        }, 1000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            onBackPressed();
//            }
//        }, 5000);
//        }
//}
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

        import java.text.SimpleDateFormat;
        import java.util.Calendar;


public class ConfirmPage extends Fragment {
    public static ConfirmPage newInstance() {
        return new ConfirmPage();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.confirm_page, container, false);
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

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat myTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat myDate = new SimpleDateFormat("MM/dd/yyyy");
        String formattedTime = myTime.format(c.getTime());
        String formattedDate = myDate.format(c.getTime());
        TextView txtView = (TextView) view.findViewById(R.id.textView4);
        txtView.setText("Current Time:\n"+formattedTime + "\n\nCurrent Date:\n" + formattedDate);
        txtView.setTextSize(40);

        final Button button = view.findViewById(R.id.ack_button);
        button.setOnClickListener(this::goToHikeAlert);
//        final Button button2 = view.findViewById(R.id.MapOverview);
//        button2.setOnClickListener(this::goToAboutPage);
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
            fm.popBackStack();
        } else {
            // Log.i("MainActivity", "nothing on backstack, calling super");
        }
    }
//    public void goToMap(View view) {
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