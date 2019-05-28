package com.example.kalimdalwai;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kalimdalwai.Fragments.DetailFragment;
import com.example.kalimdalwai.NetworkFragments.NoNetworkDetail;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String storyId = intent.getStringExtra("storyId");
        String storyName = intent.getStringExtra("storyName");
        String leftUser = intent.getStringExtra("leftUser");
        String rightUser = intent.getStringExtra("rightUser");



        this.setTitle(storyName);

        Bundle bundle = new Bundle();
        bundle.putString("id", storyId);
        bundle.putString("leftUser",leftUser);
        bundle.putString("rightUser",rightUser);

        if (isNetworkAvailable()){

            DetailFragment detailFragment = new DetailFragment();

            detailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(R.id.detailActivityContainer,detailFragment).commit();

        }else {
            NoNetworkDetail noNetworkDetail = new NoNetworkDetail();

            noNetworkDetail.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(R.id.detailActivityContainer,noNetworkDetail).commit();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
