package com.router.carwash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.router.carwash.model.apiInfo.TodayWeather;

import java.util.ArrayList;

public class TodayRecyclerViewAdapter extends RecyclerView.Adapter<TodayRecyclerViewAdapter.ViewHolder> {
    private ArrayList<TodayWeather> mData = new ArrayList<>() ;

    public TodayRecyclerViewAdapter() {

    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView = itemView.findViewById(R.id.today_time_tv) ;
            imageView = itemView.findViewById(R.id.today_Weather_iv);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    TodayRecyclerViewAdapter(ArrayList<TodayWeather> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public TodayRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.today_weather_item, parent, false) ;
        TodayRecyclerViewAdapter.ViewHolder viewHolder = new TodayRecyclerViewAdapter.ViewHolder(view) ;

        return viewHolder ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(TodayRecyclerViewAdapter.ViewHolder holder, int position) {
        TodayWeather todayWeather = mData.get(position) ;
        holder.textView.setText(todayWeather.getTime()) ;
        switch (todayWeather.getWeather()){
            case "Clear":
                holder.imageView.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                holder.imageView.setImageResource(R.drawable.cloud);
                break;
            case "Snow":
                holder.imageView.setImageResource(R.drawable.snow);
                break;
            case "Rain":
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.sun);
                break;
        }
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    void addItem(TodayWeather todayWeather) {
        // 외부에서 item을 추가시킬 함수입니다.
        mData.add(todayWeather);
    }
}
