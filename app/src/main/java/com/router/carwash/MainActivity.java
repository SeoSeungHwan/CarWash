package com.router.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private String TAG = "MainActivity TAG";

    //View 객체들
    private TextView region;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View객체들
        region = findViewById(R.id.region_tv);
        progressBar = findViewById(R.id.progressBar);

        //LocationService 객체
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        //Location Permission Check
        PermissionListener permissionlistener = new PermissionListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onPermissionGranted() {

                MainViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MainViewModel.class);
                fusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                            if (location != null) {
                                viewModel.setLat(String.valueOf(location.getLatitude()));
                                viewModel.setLon(String.valueOf(location.getLongitude()));
                                viewModel.fetchWeatherInfo();
                                viewModel.getItemLiveData().observe(MainActivity.this, weatherInfo -> {
                                    region.setText("" + weatherInfo.getCity().getName());

                                });

                                viewModel.getWeatherLiveData().observe(MainActivity.this, weatherInfo ->{
                                    for(int i=0 ; i<37;i++){
                                        Log.d(TAG, "onPermissionGranted: "+weatherInfo.get(i).getDtTxt() + ","+ weatherInfo.get(i).getWeather().get(0).getMain());
                                    }
                                });

                                viewModel.getLoadingLiveData().observe(MainActivity.this, isloading->{
                                    if(isloading){
                                        progressBar.setVisibility(View.VISIBLE);
                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                            }
                });
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                //Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();

    }
}