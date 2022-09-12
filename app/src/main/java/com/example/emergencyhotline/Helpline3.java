package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Helpline3 extends AppCompatActivity {
    TextView Allow ;
    FusedLocationProviderClient providerClient;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Bundle bundle;
    String service;
    String type;
    LocationRequest locationRequest;
    static final int RCS=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policehelpline);

        Allow = findViewById(R.id.allow_location);

        bundle = getIntent().getExtras();
        type=bundle.getString("type");

        service = bundle.getString("service");

        mAuth = FirebaseAuth.getInstance();

        providerClient= LocationServices.getFusedLocationProviderClient(this);

        Allow.setOnClickListener(new View.OnClickListener() {


            @Override


            public void onClick(View v) {
                locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(5000);
                locationRequest.setFastestInterval(2000);


                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);
                Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());

                locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                        try {

                            LocationSettingsResponse obj = locationSettingsResponseTask.getResult(ApiException.class);
                            // GPS ON
                            Toast.makeText(Helpline3.this," LOCATION SAVED! SERVICE ON THE WAY",Toast.LENGTH_LONG).show();

                        } catch (ApiException e) {
                            e.printStackTrace();
                            switch (e.getStatusCode()) {
                                case LocationSettingsStatusCodes
                                        .RESOLUTION_REQUIRED:

                                    try {
                                        ResolvableApiException exception = (ResolvableApiException) e;
                                        exception.startResolutionForResult(Helpline3.this, RCS);
                                    } catch (IntentSender.SendIntentException sendIntentException) {
                                        sendIntentException.printStackTrace();
                                    }
                                    break;

                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }
                        }


                    }
                });
                if(ActivityCompat.checkSelfPermission(Helpline3.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    providerClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if(location!=null){

                                try {
                                    Geocoder geocoder = new Geocoder(Helpline3.this, Locale.getDefault());
                                    List<Address> addressList= geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);


                                    String longitude=""+addressList.get(0).getLongitude();
                                    String latitude=""+addressList.get(0).getLatitude();
                                    String country =""+addressList.get(0).getCountryName();
                                    String address =""+addressList.get(0).getAddressLine(0);
                                    LocationHelper helper= new LocationHelper(service,type,longitude,latitude,country,address);

                                    firebaseDatabase= FirebaseDatabase.getInstance();
                                    reference=firebaseDatabase.getReference("Police Helpline Locations");
                                    reference.child(service+","+address).setValue(helper);
                                    //Toast.makeText(Helpline3.this,"LOCATION SAVED! SERVICE ON THE WAY",Toast.LENGTH_LONG).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                else{
                    ActivityCompat.requestPermissions(Helpline3.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RCS){
            switch (requestCode){
                case Activity
                        .RESULT_OK:
                    Toast.makeText(Helpline3.this, "Location turned On", Toast.LENGTH_SHORT).show();
                    break;
                case Activity
                        .RESULT_CANCELED:
                    Toast.makeText(Helpline3.this, "location is required", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}