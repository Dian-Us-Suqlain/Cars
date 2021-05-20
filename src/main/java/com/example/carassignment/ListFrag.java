package com.example.carassignment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {

    View v;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CarAdapter carAdapter;

    public ListFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = v.findViewById(R.id.cars_list);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        carAdapter = new CarAdapter(this.getActivity(), ApplicationClass.cars);
        recyclerView.setAdapter(carAdapter);
    }


    public void notifyDataChanged() {
        carAdapter.notifyDataSetChanged();
    }
}
