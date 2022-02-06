package com.example.busanbusstation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder>{
    ArrayList<Station> items = new ArrayList<Station>();

    public void setItems(ArrayList<Station> items) {
        this.items = items;
    }

    public void clearItems(){
        items.clear();
    }

    public void addItem(Station station){
        items.add(station);
    }

    //onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.stations_item, parent, false);
        return new ViewHolder(itemView);
    }

    //onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Station item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //아이템 뷰를 저장하는 뷰홀더 클래스
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bus_id;
        TextView bus_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //id 매핑
            bus_id = itemView.findViewById(R.id.bus_id);
            bus_name = itemView.findViewById(R.id.bus_name);

        }

        public void setItem(Station station){
            bus_id.setText(station.bstopid);
            bus_name.setText(station.bstopnm);
        }
    }
}
