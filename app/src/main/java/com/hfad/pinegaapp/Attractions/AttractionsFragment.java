package com.hfad.pinegaapp.Attractions;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.pinegaapp.Attractions.Attractions;
import com.hfad.pinegaapp.Attractions.DataAdapterRecyclerView;
import com.hfad.pinegaapp.R;

import java.util.Arrays;


public class AttractionsFragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attractions, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layout = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layout);

        DataAdapterRecyclerView dataAdapterRecyclerView = new DataAdapterRecyclerView(getContext(), Arrays.asList(Attractions.attractionsList));

        recyclerView.setAdapter(dataAdapterRecyclerView);

    }



}