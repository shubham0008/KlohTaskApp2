package com.klohtaskapp.klohtaskapp.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.klohtaskapp.klohtaskapp.Adapters.EventListAdapter;
import com.klohtaskapp.klohtaskapp.Models.EventListCardModel;
import com.klohtaskapp.klohtaskapp.R;
import com.klohtaskapp.klohtaskapp.Utility.ApiClient;
import com.klohtaskapp.klohtaskapp.Utility.ApiInterface;


import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG ="API_DEBUG_LOG" ;
    ApiInterface apiService;
    private ArrayList<EventListCardModel> eventList = new ArrayList<>();
    private double lat;
    private double lon;
    private RecyclerView recyclerView;

    private Location mLastLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private double latt,lont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        apiService = ApiClient.getClient().create(ApiInterface.class);
       //default check locations
        lat=12.926031;
        lon=77.676246;

        //Net Connection Issue
        if (isNetConnected(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Connected to Internet", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Not Connected to Internet", Toast.LENGTH_SHORT).show();

        }


        recyclerView = (RecyclerView) findViewById(R.id.tag_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Building the GoogleApi client
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }
        // First we need to check availability of play services
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }
        // Show location button click listener
        getLocation();


    }
    @SuppressLint("MissingPermission")
    private void getLocation() {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
//            lat = mLastLocation.getLatitude();
//            lon = mLastLocation.getLongitude();
            getEventList(lat,lon);
            String k = "longitude:" + lon + " lattitude:" + lat;
            Log.d(getPackageName(), "displayLocation: " + k);
        } else {
            Log.d(getPackageName(), "displayLocation: " + "Couldn't get the location. Make sure location is enabled on the device");
        }
    }
    /**
     * Creating google api client object
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }
    /**
     * Method to verify google play services on the device
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }
    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("TAG", "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {
        // Once connected with google api, get the location
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }


    void getEventList(double lat, double lon) {
        RequestBody payLoadBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),"{  \"location\":{\n" +
                "\t\"lat\":"+ lat+",\n" +
                "\n" +
                "\t\"lon\":"+lon+"\n" +
                "\n" +
                "}\n" +
                "}");


        Call<JsonElement> call = apiService.getEventList(payLoadBody);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement>call, Response<JsonElement> response) {

                JsonParser parser = new JsonParser();
                JsonObject object = (JsonObject) parser.parse(String.valueOf(response.body()));
                if(object.getAsJsonObject("response").getAsJsonArray("results").size()!=0) {
                    for (int i = 0; i < 5; i++) {
                        String meventPicUrl = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().get("imageUrl") + "";
                        String meventTitle = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().get("title") + "";
                        String meventHostPicture = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().get("ownerProfileImageUrl") + "";
                        String meventSummary = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().get("summary") + "";
                        String meventLocation = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().getAsJsonObject("location").get("name") + "";
                        String meventTime = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().getAsJsonObject("activityTime").get("activityDateString") + "";
                        String meventId = object.getAsJsonObject("response").getAsJsonArray("results").get(i).getAsJsonObject().get("activityId") + "";

                        EventListCardModel eventData = new EventListCardModel(meventPicUrl, meventTitle, meventHostPicture, meventSummary, meventLocation, meventTime, meventId);
                        // Log.d(eventData.getEventId(),eventData.getEventSummary());
                        eventList.add(eventData);

                    }
                }
               recyclerView.setAdapter(new EventListAdapter(eventList,getApplicationContext()));
                Log.d(TAG, "Response "+object.getAsJsonObject("response").getAsJsonArray("results").size());
            }

            @Override
            public void onFailure(Call<JsonElement>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }



}
