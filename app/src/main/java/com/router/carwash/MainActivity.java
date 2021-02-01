package com.router.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.router.carwash.model.Weather;
import com.router.carwash.repository.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "TEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

       WeatherService service = retrofit.create(WeatherService.class);


        Call<Weather> weatherCall =  (Call<Weather>) service.getJson(
                "202102010600"
        );

        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: 실패");
                }else {
                    Log.d(TAG, "onResponse: 성공");


                    Log.d(TAG, "onResponse: 성공" + response.body().getResponse().getHeader().getResultCode());

                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }
        });

    }
}