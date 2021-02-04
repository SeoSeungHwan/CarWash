package com.router.carwash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
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
import com.router.carwash.model.apiInfo.TodayWeather;
import com.router.carwash.model.apiInfo.Weather;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private String TAG = "MainActivity TAG";
    private ArrayList<String> weatherList = new ArrayList<>();
    private long backBtnTime = 0;
    private Toast toast;

    //View 객체들
    private TextView region;
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    TodayRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    private ImageView afternoon_1_iv, afternoon_2_iv, afternoon_3_iv, afternoon_4_iv;

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View객체들
        region = findViewById(R.id.region_tv);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        //RecyclerViewadapter , LinearLayout생성
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TodayRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        //LocationService 객체
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        PermissionListener permissionlistener = new PermissionListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                            //지역이름 Setting
                            region.setText("" + weatherInfo.getCity().getName());

                            //날씨판단
                            Log.d(TAG, "onPermissionGranted: " + weatherInfo.getListitem().get(3).getDtTxt().substring(11, 13));
                            String now = LocalDate.now().toString();

                            for(int i=0 ; i< weatherInfo.getListitem().size(); i++){
                                if(now.equals(weatherInfo.getListitem().get(i).getDtTxt().substring(0,10))){

                                    TodayWeather todayWeather = new TodayWeather();
                                    todayWeather.setTime(weatherInfo.getListitem().get(i).getDtTxt().substring(11, 16));
                                    todayWeather.setWeather(weatherInfo.getListitem().get(i).getWeather().get(0).getMain());
                                    adapter.addItem(todayWeather);
                                    Log.d(TAG, "onPermissionGranted: " + weatherInfo.getListitem().get(i).getDtTxt().substring(11, 16)+weatherInfo.getListitem().get(i).getWeather().get(0).getMain());
                                }
                            }
                            adapter.notifyDataSetChanged();

                        });
                            /*//5일간의 날씨 판단
                            weatherList.add(whatIsWeather(weatherInfo.getListitem().get(0).getWeather().get(0)));
                            for (int i = 0; i < 9; i++) {
                                boolean isClouds = false;
                                for (int j = 1; j < 5; j++) {
                                    //비나 눈이오면 해당 시간대는 비나 눈으로 판단하고 break
                                    if (whatIsWeather(weatherInfo.getListitem().get(i * 4 + j).getWeather().get(0)).equals("rain")) {
                                        weatherList.add("rain");
                                        break;
                                    } else if (whatIsWeather(weatherInfo.getListitem().get(i * 4 + j).getWeather().get(0)).equals("snow")) {
                                        weatherList.add("snow");
                                        break;
                                    } else if (whatIsWeather(weatherInfo.getListitem().get(i * 4 + j).getWeather().get(0)).equals("clouds")) {
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

                                                        for (int index = 0; index < weatherList.size(); index++) {
                                Log.d(TAG, "onPermissionGranted: " + weatherList.get(index));
                            }*/




                        viewModel.getLoadingLiveData().observe(MainActivity.this, isloading -> {
                            if (isloading) {
                                progressBar.setVisibility(View.VISIBLE);
                            } else {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }else{
                    }
                });
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();

    }


}
