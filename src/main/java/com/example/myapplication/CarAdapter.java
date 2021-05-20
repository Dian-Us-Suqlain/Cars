package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    ArrayList<Car> cars;  //List of Cars
    CarSelected context;


    public interface CarSelected{ //To store the index of car is selected
        void onItemClicked(int index);
    }


    public CarAdapter(Context context, ArrayList<Car> list){
        cars = list;
        this.context = (CarSelected) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCarImage;
        TextView tvCarModel, tvOwnerName;

        public ViewHolder(View itemView) {
            super(itemView);

            ivCarImage = itemView.findViewById(R.id.iv_car_image);
            tvCarModel = itemView.findViewById(R.id.tv_car_model);
            tvOwnerName = itemView.findViewById(R.id.tv_owner_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(cars.indexOf(view.getTag()));
                }
            });
        }
    }


    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_layout, parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(cars.get(position));

        holder.tvOwnerName.setText(cars.get(position).getOwnerName());
        holder.tvCarModel.setText(cars.get(position).getCarModel());

        if ("Volkswagen".equals(cars.get(position).getCarName())) {
            holder.ivCarImage.setImageResource(R.drawable.volkswagen);
        } else if ("Nissan".equals(cars.get(position).getCarName())) {
            holder.ivCarImage.setImageResource(R.drawable.nissan);
        } else if ("Mercedes".equals(cars.get(position).getCarName())) {
            holder.ivCarImage.setImageResource(R.drawable.mercedes);
        } else {
            holder.ivCarImage.setImageResource(R.drawable.car_new);
        }
    }


    @Override
    public int getItemCount() {
        return cars.size();
    }
}

