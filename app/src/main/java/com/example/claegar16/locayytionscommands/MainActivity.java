package com.example.claegar16.locayytionscommands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesClient;
//import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class MainActivity extends AppCompatActivity {
    private GoogleMap mMap;
    private GoogleApiClient mGooleApiClient;
    private double myLat;
    private double myLong;

    public double getMyLat(){
        return myLat;
    }

    public double getMyLong(){
        return myLong;
    }

    public void setMyLat(double myLat) {
        myLat = this.myLat;
    }

    public void setMyLong(double myLong) {
        myLong = this.myLong;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void storeLocation(View view) {









//        setContentView(R.layout.activity_main);

        // 2. get reference to TextView
//        txtLong = (TextView) findViewById(R.id.txtLong);
//        txtLat = (TextView) findViewById(R.id.txtLat);
//
//        // 3. create LocationClient
//        mLocationClient = new LocationClient(this, this, this);
//
//        // 4. create & set LocationRequest for Location update
//        mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        // Set the update interval to 5 seconds
//        mLocationRequest.setInterval(1000 * 5);
//        // Set the fastest update interval to 1 second
//        mLocationRequest.setFastestInterval(1000 * 1);


//        mGooleApiClient = new GoogleApiClient.Builder.(this);

//        LocationRequest = LocationRequest.create()
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//            .setInterval(10 *1000)
//            .setFastestInterval(1 * 1000);




//        myLat = location.getLatitude();
//        myLong=
//        LatLng myLocation = new LatLng(myLat, myLong);

    }
}
