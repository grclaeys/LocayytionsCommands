package com.example.claegar16.locayytionscommands;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.IntentSender;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public double currentLatitude;
    public double currentLongitude;
    public double ayyLat;
    public double lmaoLong;
    public long savedLat;
    public long savedLong;

    public Location myLocation;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest LocationRequest;

    public static final String LAT = "AOP_PREFS";
    public static final String LAT_KEY = "AOP_PREFS_String";
    public static final String LONG = "AOP_PREFS2";
    public static final String LONG_KEY = "AOP_PREFS_String2";
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        LocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in millisecondsqwed
        mGoogleApiClient.connect(); //makes it work
        MediaPlayer mp = MediaPlayer.create(context, R.raw.johncena);
        mp.start();


    }
    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleNewLocation(Location location) {


        Log.d(TAG, location.toString());
        Log.d(TAG, location.toString());
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        myLocation = location;
    }

    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(location == null){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, LocationRequest, this);
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    public void printSavedLocation(View view) {
        LatLng coords = getSavedLocation(MainActivity.getAppContext());

        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
        TextView radioButton = (TextView) findViewById(R.id.radioButton);
        if(coords.latitude == 0.0 && coords.longitude == 0.0){
            radioButton.setText("location unavailable, turn on locaiton in settings");
        }else

        radioButton.setText(coords.latitude + " " + coords.longitude);
    }

    public void storeLocation(View view) {

        savedLat = (long) (currentLatitude * 1000);
        savedLong = (long) (currentLongitude * 1000);

        ayyLat = (double) savedLat / 1000;
        lmaoLong = (double) savedLong / 1000;


        SharedPreferences.Editor editor2;

        SharedPreferences savedLongSP = getSharedPreferences(LONG, Context.MODE_PRIVATE);
        editor2 = savedLongSP.edit();
        editor2.putLong(LONG_KEY, savedLong);
        editor2.commit();

        SharedPreferences.Editor editor;

        SharedPreferences savedLatSP = getSharedPreferences(LAT, Context.MODE_PRIVATE);

        editor = savedLatSP.edit();
        editor.putLong(LAT_KEY, savedLat);
        editor.commit();



        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
        TextView radioButton2 = (TextView) findViewById(R.id.radioButton2);

        if (ayyLat == 0.0 && lmaoLong == 0.0) {
            radioButton2.setText("location unavailable, turn on location in settings");

        } else
            radioButton2.setText(ayyLat + " " + lmaoLong);
    }

    public LatLng getSavedLocation (Context context) {


        SharedPreferences savedLatSP = context.getSharedPreferences(LAT, Context.MODE_PRIVATE);
        ayyLat = (double) savedLatSP.getLong(LAT_KEY, savedLat);

        ayyLat /= 1000;

        SharedPreferences savedLongSP = context.getSharedPreferences(LONG, Context.MODE_PRIVATE);
        lmaoLong = (double) savedLongSP.getLong(LONG_KEY, savedLong);

        lmaoLong /= 1000;

        LatLng coords = new LatLng(ayyLat, lmaoLong);
        
        return coords;
    }

    public void mapButton(View view){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
