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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalimdalwai.Fragments.DetailFragment;
import com.example.kalimdalwai.R;

import java.util.Objects;

public class NoNetworkDetail extends Fragment {

    Button retryButton;
    Context context;
    String id,leftUser,rightUser;
    Bundle bundle1;

    public NoNetworkDetail() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_network_main, container, false);

        context = getActivity();
        retryButton = view.findViewById(R.id.retryButtonNoNetworkMain);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            id = bundle.getString("id");
            leftUser = bundle.getString("leftUser");
            rightUser = bundle.getString("rightUser");

            bundle1 = new Bundle();
            bundle1.putString("id", id);
            bundle1.putString("leftUser",leftUser);
            bundle1.putString("rightUser",rightUser);

        }

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()){
                    Toast.makeText(context, "Network Available Now", Toast.LENGTH_SHORT).show();
                    DetailFragment nextFrag = new DetailFragment();
                    nextFrag.setArguments(bundle1);
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detailActivityContainer, nextFrag, "findThisFragment")
                            .commit();
                }else{
                    Toast.makeText(context, "No network", Toast.LENGTH_SHORT).show();
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
