package com.example.carassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{

    ArrayList<Car> cars;  //List of Cars
    CarSelected context;


    public interface CarSelected{ //To store the index of car is selected
        void onItemClicked(int i);
    }


    public CarAdapter(Context context, ArrayList<Car> list){
        cars = list;
        this.context = (CarSelected) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCarImage;
        TextView tvCarModel, tvOwnerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCarImage = itemView.findViewById(R.id.iv_car_image);
            tvCarModel = itemView.findViewById(R.id.tv_car_model);
            tvOwnerName = itemView.findViewById(R.id.tv_owner_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(cars.indexOf((view.getTag())));
                }
            });
        }
    }


    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_layout, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(cars.get(position));

        holder.tvOwnerName.setText(cars.get(position).getOwnerName());
        holder.tvCarModel.setText(cars.get(position).getCarModel());

        switch (cars.get(position).getCarName()) {
            case "Volkswagen":
                holder.ivCarImage.setImageResource(R.drawable.volkswagen);
                break;
            case "Nissan":
                holder.ivCarImage.setImageResource(R.drawable.nissan);
                break;
            case "Mercedes":
                holder.ivCarImage.setImageResource(R.drawable.mercedes);
                break;
            default:
                holder.ivCarImage.setImageResource(R.drawable.car_new);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
