package com.example.kalimdalwai.NetworkFragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kalimdalwai.Fragments.MainFragment;
import com.example.kalimdalwai.R;

import java.util.Objects;

public class NoNetworkMain extends Fragment {

    Button retryButton;
    Context context;

    public NoNetworkMain() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_network_main, container, false);

        context = getActivity();

        retryButton = view.findViewById(R.id.retryButtonNoNetworkMain);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()){

                    MainFragment mainFragment= new MainFragment();

                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainActivityContainer, mainFragment, "mainFragment")
                            .commit();

                }else{
                    Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
