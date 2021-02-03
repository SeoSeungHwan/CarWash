package com.router.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.router.carwash.model.apiInfo.Main;
import com.router.carwash.model.apiInfo.Weather;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private String TAG = "MainActivity TAG";
    private ArrayList<String> weatherList = new ArrayList<>();
    private long backKeyPressedTime = 0;
    private Toast toast;

    //View 객체들
    private TextView region;
    private ProgressBar progressBar;
    private ImageView morning_1_iv, morning_2_iv, morning_3_iv, morning_4_iv, morning_5_iv;
    private ImageView afternoon_1_iv, afternoon_2_iv, afternoon_3_iv, afternoon_4_iv;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

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
                            //region.setGravity(TextView.TEXT_ALIGNMENT_CENTER);

                        });

                        viewModel.getWeatherLiveData().observe(MainActivity.this, weatherInfo -> {
                            weatherList.add(whatIsWeather(weatherInfo.get(0).getWeather().get(0)));
                            for (int i = 0; i < 9; i++) {
                                boolean isClouds = false;
                                for (int j = 1; j < 5; j++) {
                                    //비나 눈이오면 해당 시간대는 비나 눈으로 판단하고 break
                                    if (whatIsWeather(weatherInfo.get(i * 4 + j).getWeather().get(0)).equals("rain")) {
                                        weatherList.add("rain");
                                        break;
                                    } else if (whatIsWeather(weatherInfo.get(i * 4 + j).getWeather().get(0)).equals("snow")) {
                                        weatherList.add("snow");
                                        break;
                                    } else if (whatIsWeather(weatherInfo.get(i * 4 + j).getWeather().get(0)).equals("clouds")) {
                                        isClouds = true;
                                    }

                                    //눈이나 비가없을때 구름인지 맑음인지 정하기
                                    if (isClouds == true && j == 4) {
                                        weatherList.add("clouds");
                                    } else if (isClouds == false && j == 4) {
                                        weatherList.add("clear");
                                    }
                                }
                            }
                        });

                        for (int index = 0; index < weatherList.size(); index++) {
                            System.out.println("sibal" + weatherList.get(index));
                        }

                        viewModel.getLoadingLiveData().observe(MainActivity.this, isloading -> {
                            if (isloading) {
                                progressBar.setVisibility(View.VISIBLE);
                            } else {
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


    //weather객체 넣고 어떤 날씨인지 반환하는 함수
    private String whatIsWeather(Weather weather) {
        switch (weather.getMain()) {
            case "clear":
                return "clear";
            case "clouds":
                return "clouds";
            case "snow":
                return "snow";
            case "rain":
                return "rain";
            default:
                return "null";
        }
    }
}
