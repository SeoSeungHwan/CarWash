package com.router.carwash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.router.carwash.model.apiInfo.TodayWeather;
import com.router.carwash.model.apiInfo.WeatherInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private String TAG = "MainActivity TAG";
    private ArrayList<String> weatherList = new ArrayList<>();
    private long backBtnTime = 0;

    //View 객체들
    private TextView region , today_date_tv , temporatures_tv , status_tv;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button googleMap_Btn;
    private TodayRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView afternoon_1_iv, afternoon_2_iv, afternoon_3_iv, afternoon_4_iv;
    private TextView day_1_tv, day_2_tv, day_3_tv, day_4_tv;

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //View객체들
        region = findViewById(R.id.region_tv);
        today_date_tv = findViewById(R.id.today_date_tv);
        temporatures_tv = findViewById(R.id.temporatures_tv);
        googleMap_Btn = findViewById(R.id.googleMap_btn);
        status_tv = findViewById(R.id.status_tv);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        day_1_tv = findViewById(R.id.day_1_tv); day_2_tv = findViewById(R.id.day_2_tv);
        day_3_tv = findViewById(R.id.day_3_tv); day_4_tv = findViewById(R.id.day_4_tv);
        afternoon_1_iv = findViewById(R.id.afternoon_1_iv); afternoon_2_iv = findViewById(R.id.afternoon_2_iv);
        afternoon_3_iv = findViewById(R.id.afternoon_3_iv); afternoon_4_iv = findViewById(R.id.afternoon_4_iv);


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
                            String status = "GREAT";


                            //지역이름  , 날짜 Setting
                            region.setText("<" + weatherInfo.getCity().getName()+">");
                            today_date_tv.setText(LocalDate.now().toString());
                            temporatures_tv.setText(String.format("%.1f",weatherInfo.getListitem().get(2).getMain().getTemp()-273.15) + "C");
                            day_1_tv.setText(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MM.dd")).toString());
                            day_2_tv.setText(LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM.dd")).toString());
                            day_3_tv.setText(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("MM.dd")).toString());
                            day_4_tv.setText(LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("MM.dd")).toString());

                            //오늘 날씨 RecyclerView 등록
                            String now = LocalDate.now().toString();

                            for (int i = 0; i < weatherInfo.getListitem().size(); i++) {
                                if (now.equals(weatherInfo.getListitem().get(i).getDtTxt().substring(0, 10))) {

                                    TodayWeather todayWeather = new TodayWeather();
                                    todayWeather.setTime(weatherInfo.getListitem().get(i).getDtTxt().substring(11, 16));
                                    todayWeather.setWeather(weatherInfo.getListitem().get(i).getWeather().get(0).getMain());
                                    if(todayWeather.getWeather().equals("Rain")||todayWeather.getWeather().equals("Snow")){
                                        status = "BAD";
                                    }
                                    adapter.addItem(todayWeather);

                                }
                            }
                            adapter.notifyDataSetChanged();

                            //2~5일간의 날씨 등록
                            String[] day = {"Clear" , "Clear" , "Clear" , "Clear"};
                            for(int i=0 ; i< weatherInfo.getListitem().size() ; i++){
                                String str = weatherInfo.getListitem().get(i).getDtTxt();
                                for(int z=1 ; z<5 ; z++){
                                    if(str.substring(8,10).equals(LocalDate.now().plusDays(z).format(DateTimeFormatter.ofPattern("dd")))) {
                                        if (getWeatherMain(weatherInfo, i).equals("Snow")) {
                                            day[z-1] = "Snow";
                                            status = "BAD";
                                        } else if (getWeatherMain(weatherInfo, i).equals("Rain")) {
                                            day[z-1] = "Rain";
                                            status = "BAD";
                                        } else if (!(day[z-1].equals("Snow") || day[z-1].equals("Rain")) && getWeatherMain(weatherInfo, i).equals("Clouds")) {
                                            day[z-1] = "Clouds";
                                        }
                                    }
                                }
                            }


                            afternoon_1_iv.setImageResource(imageViewSrc(day[0]));
                            afternoon_2_iv.setImageResource(imageViewSrc(day[1]));
                            afternoon_3_iv.setImageResource(imageViewSrc(day[2]));
                            afternoon_4_iv.setImageResource(imageViewSrc(day[3]));
                            status_tv.setText(status);

                        });

                        googleMap_Btn.setOnClickListener(view -> {
                            Intent intent = new Intent(MainActivity.this, KakaoMapActivity.class);
                            intent.putExtra("lat" , viewModel.getLat());
                            intent.putExtra("lon" , viewModel.getLon());
                            startActivity(intent);
                        });

                        viewModel.getLoadingLiveData().observe(MainActivity.this, isloading -> {
                            if (isloading) {
                                progressBar.setVisibility(View.VISIBLE);
                            } else {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                    else {
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

    private String getWeatherMain(WeatherInfo weatherInfo , int index){
        return weatherInfo.getListitem().get(index).getWeather().get(0).getMain();
    }

    private int imageViewSrc(String weather){
        switch (weather){
            case "Clear":
                return R.drawable.sun;
            case "Clouds":
                return R.drawable.cloud;
            case "Snow":
                return R.drawable.snow;
            case "Rain":
                return R.drawable.rain;
            default:
                return R.drawable.sun;
        }
    }

}
