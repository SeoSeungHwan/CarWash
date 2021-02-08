package com.router.carwash;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.router.carwash.model.apiInfo.Document;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarWashRecyclerViewAdapter extends RecyclerView.Adapter<CarWashRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Document> mData = new ArrayList<>() ;
    public ArrayList<Document> getmData() {
        return mData;
    }

    public CarWashRecyclerViewAdapter() {

    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView place_name_tv ,phone_tv,road_address_name_tv ,distance_tv;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            place_name_tv = itemView.findViewById(R.id.place_name_tv) ;
            phone_tv = itemView.findViewById(R.id.phone_tv) ;
            road_address_name_tv = itemView.findViewById(R.id.road_address_name_tv) ;
            distance_tv = itemView.findViewById(R.id.distance_tv);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    CarWashRecyclerViewAdapter(ArrayList<Document> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public CarWashRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.carwash_item, parent, false) ;
        CarWashRecyclerViewAdapter.ViewHolder viewHolder = new CarWashRecyclerViewAdapter.ViewHolder(view) ;

        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Document document = mData.get(position) ;
        holder.place_name_tv.setText(document.getPlaceName());
        if(document.getPhone().equals("")){
            holder.phone_tv.setText("전화번호 없음");
        }else{
            holder.phone_tv.setText(document.getPhone());
        }
        if(document.getRoadAddressName().equals("")){
            holder.road_address_name_tv.setText("주소 없음");
        }else{
            holder.road_address_name_tv.setText(document.getRoadAddressName());
        }
        holder.distance_tv.setText(Double.parseDouble(document.getDistance())/1000 + "km");


    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    void addItem(Document document) {
        // 외부에서 item을 추가시킬 함수입니다.
        mData.add(document);

    }
}
