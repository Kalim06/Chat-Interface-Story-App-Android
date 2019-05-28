package com.example.kalimdalwai.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalimdalwai.Adapter.MessageAdapter;
import com.example.kalimdalwai.Data.Data;
import com.example.kalimdalwai.NetworkFragments.NoNetworkDialog;
import com.example.kalimdalwai.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class DetailFragment extends Fragment {

    Context context;
    String id = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    String poem, leftUser, rightUser;
    RecyclerView recyclerView;
    ArrayList<Data> RecyclerList;
    int recyclerListSize, i = 1;
    String[] arrSplit;
    MessageAdapter messageAdapter;
    View viewLayout;
    TextView leftUserTextView, rightUserTextView;

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        context = getActivity();
        viewLayout = view.findViewById(R.id.detailFragment);
        leftUserTextView = view.findViewById(R.id.leftUser);
        rightUserTextView = view.findViewById(R.id.rightUser);
        RecyclerList = new ArrayList<>();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
            leftUser = bundle.getString("leftUser");
            rightUser = bundle.getString("rightUser");
        }

        leftUserTextView.setText(leftUser);
        rightUserTextView.setText(rightUser);



        databaseReference = database.getReference().child("Story").child(id).child("story");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String story = dataSnapshot.getValue(String.class);

                getData(story);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = view.findViewById(R.id.recyclerViewDetailFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    LoadData();
                } else {
                    NoNetworkDialog dialogFragment = new NoNetworkDialog();
                    assert getFragmentManager() != null;
                    dialogFragment.show(getFragmentManager(), "Network Dialog");
                }
            }
        });

        return view;
    }

    private void getData(String S) {

        poem = S;

        arrSplit = poem.split("/n");

        String first = arrSplit[0];

        Data m = new Data(first);


        RecyclerList.add(m);
        messageAdapter = new MessageAdapter(context, RecyclerList, this);
        recyclerView.setAdapter(messageAdapter);
    }

    public void LoadData() {
        if (isNetworkAvailable()) {
            if (i < arrSplit.length) {
                recyclerListSize = RecyclerList.size();

                String temp = arrSplit[i];

                Data m = new Data(temp);

                RecyclerList.add(m);

                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(recyclerListSize);
                recyclerView.smoothScrollToPosition(recyclerListSize);

                i++;
            } else {
                Toast.makeText(context, "Story Ended", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}