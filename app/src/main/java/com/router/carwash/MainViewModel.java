package com.router.carwash;

import android.util.Log;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.router.carwash.model.apiInfo.WeatherInfo;
import com.router.carwash.repository.WeatherService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static android.content.ContentValues.TAG;

public class MainViewModel extends ViewModel {


    private String lat = "38";
    private String lon = "125";

    private MutableLiveData<WeatherInfo> itemLiveData = new MutableLiveData<>();



    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();


    //Retrofit사용하여 api를통해 request
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WeatherService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    WeatherService service = retrofit.create(WeatherService.class);

    public void fetchWeatherInfo(){

        //로딩 시작
        loadingLiveData.setValue(true);


        Call<WeatherInfo> weatherInfoCall = service.getJson(lat,lon);
        weatherInfoCall.clone().enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: 실패");
                }else {

                    Log.d(TAG, "onResponse: 성공" );
                    WeatherInfo weatherInfo = response.body();
                    itemLiveData.postValue(weatherInfo);
                }

                //로딩 끝
                loadingLiveData.postValue(false);
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                    itemLiveData.postValue(null);
            }
        });
    }


    //getter setter
    public MutableLiveData<WeatherInfo> getItemLiveData() {
        return itemLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
