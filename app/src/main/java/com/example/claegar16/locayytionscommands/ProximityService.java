package com.example.claegar16.locayytionscommands;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ProximityService extends IntentService implements
                              Runnable,
                              GoogleApiClient.ConnectionCallbacks,
                              GoogleApiClient.OnConnectionFailedListener,
                              LocationListener {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can  perform, e.g. ACTION_FETCH_NEW_ITEMS
    public double currentLatitude;
    public double currentLongitude;
    public double issyLat = 47.85223;
    public double issyLong = 122.0288;
    public Location myLocation;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private LocationRequest LocationRequest;
    private Context context = this;

    public static final String LAT = "AOP_PREFS";
    public static final String LAT_KEY = "AOP_PREFS_String";
    public static final String LONG = "AOP_PREFS2";
    public static final String LONG_KEY = "AOP_PREFS_String2";
    public long savedLat;
    public long savedLong;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public void run() {}

    public ProximityService() {
        super("ProximityService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect(); //makes it work

        LocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        SharedPreferences savedLatSP = context.getSharedPreferences(LAT, Context.MODE_PRIVATE);
        currentLatitude = (double) savedLatSP.getLong(LAT_KEY, savedLat);

        currentLatitude /= 10000;

        SharedPreferences savedLongSP = context.getSharedPreferences(LONG, Context.MODE_PRIVATE);
        currentLongitude = (double) savedLongSP.getLong(LONG_KEY, savedLong);

        currentLongitude /= 10000;

        LatLng coords = new LatLng(currentLongitude, currentLatitude);

        new Thread(new Runnable() {
            public void run() {

                    if (Math.abs(currentLatitude - issyLat) >= .0005 && Math.abs(currentLongitude - issyLong) >= .0005) { //if close to ihs
                        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                       //turn sound off, vibrate on
                    }

                }
        }).start();
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
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, LocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
        else {
            handleNewLocation(location);
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
//        if (connectionResult.hasResolution()) {
//            try {
//                // Start an Activity that tries to resolve the error
//                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
//            } catch (IntentSender.SendIntentException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }
}
