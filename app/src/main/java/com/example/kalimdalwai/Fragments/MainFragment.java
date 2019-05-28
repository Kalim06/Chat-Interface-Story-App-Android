package com.example.kalimdalwai.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kalimdalwai.Adapter.TitleAdapter;
import com.example.kalimdalwai.Data.Data;
import com.example.kalimdalwai.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    TitleAdapter titleAdapter;
    RecyclerView recyclerView;
    ArrayList<Data> list;
    Context context;
    ProgressBar progressBar;


    public MainFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        context = getActivity();

        recyclerView = view.findViewById(R.id.mainFragmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        progressBar = view.findViewById(R.id.mainFragmentProgressBar);

        databaseReference = database.getReference().child("Titles");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Data title = dataSnapshot1.getValue(Data.class);
                    assert title != null;
                    list.add(title);
                }
                titleAdapter = new TitleAdapter(context,list);

                recyclerView.setAdapter(titleAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
