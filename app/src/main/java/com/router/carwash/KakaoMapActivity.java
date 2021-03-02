package com.router.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.router.carwash.model.apiInfo.CarWashInfo;
import com.router.carwash.model.apiInfo.Document;
import com.router.carwash.repository.CarWashService;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class KakaoMapActivity extends AppCompatActivity {

    private String lat;
    private String lon;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CarWashRecyclerViewAdapter adapter;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_map);



        //RecyclerViewadapter , LinearLayout생성
        recyclerView = findViewById(R.id.carwash_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CarWashRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        //현재위치 MainActivity에서 받아오기
        Intent intent = getIntent();
        lat = intent.getStringExtra("lat");
        lon = intent.getStringExtra("lon");

        //Retrofit사용하여 kakao api 를 사용하여 세차장 정보 가져오기
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CarWashService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        CarWashService service = retrofit.create(CarWashService.class);
        Call<CarWashInfo> carWashServiceCall = service.getSearchKeyword(
                "세차장",
                Double.parseDouble(lon),
                Double.parseDouble(lat),
                20000);
        carWashServiceCall.clone().enqueue(new Callback<CarWashInfo>() {
            @Override
            public void onResponse(Call<CarWashInfo> call, Response<CarWashInfo> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(KakaoMapActivity.this, "위치정보가 잘못되었습니다. 다시 실행해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    mapView = new MapView(KakaoMapActivity.this);
                    ViewGroup mapViewContainer = findViewById(R.id.map_view);
                    mapViewContainer.addView(mapView);

                    mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(lat), Double.parseDouble(lon)), true);
                    mapView.setZoomLevel(6,true);
                    MapPOIItem marker = new MapPOIItem();
                    marker.setItemName("Now Location");
                    marker.setTag(0);
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(lat), Double.parseDouble(lon)));
                    marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                    mapView.addPOIItem(marker);

                    for( int i=0 ; i<response.body().getDocuments().size(); i++){

                        MapPOIItem CarWash_marker = new MapPOIItem();
                        CarWash_marker.setItemName(response.body().getDocuments().get(i).getPlaceName()+" "+response.body().getDocuments().get(i).getPhone());
                        CarWash_marker.setTag(0);
                        CarWash_marker.setMapPoint(MapPoint.mapPointWithGeoCoord(response.body().getDocuments().get(i).getY(),response.body().getDocuments().get(i).getX()));
                        CarWash_marker.setMarkerType(MapPOIItem.MarkerType.RedPin);
                        mapView.addPOIItem(CarWash_marker);

                        adapter.addItem(response.body().getDocuments().get(i));
                    }

                    adapter.notifyDataSetChanged();
                    Toast.makeText(KakaoMapActivity.this, "현재위치 기준으로 2km내의 세차장입니다.", Toast.LENGTH_SHORT).show();
                }

                //거리가 가까운 순으로 세차장 정렬
                Collections.sort(adapter.getmData(), new Comparator<Document>() {
                    @Override
                    public int compare(Document document1, Document document2) {
                        if(Double.parseDouble(document1.getDistance())> Double.parseDouble(document2.getDistance())) return 1;
                        if(Double.parseDouble(document1.getDistance())< Double.parseDouble(document2.getDistance())) return -1;
                        return 0;
                    }
                });

                adapter.setOnItemClickListener(new CarWashRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(
                                adapter.getmData().get(position).getY(),
                                adapter.getmData().get(position).getX()),
                                true);
                    }
                });

            }

            @Override
            public void onFailure(Call<CarWashInfo> call, Throwable t) {

            }
        });

    }

}