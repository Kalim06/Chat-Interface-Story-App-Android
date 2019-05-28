package com.example.kalimdalwai;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kalimdalwai.Fragments.MainFragment;
import com.example.kalimdalwai.NetworkFragments.NoNetworkMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNetworkAvailable()){
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mainActivityContainer,fragment).commit();

        }else{
            NoNetworkMain fragment = new NoNetworkMain();
            getSupportFragmentManager().beginTransaction().add(R.id.mainActivityContainer,fragment).commit();

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
