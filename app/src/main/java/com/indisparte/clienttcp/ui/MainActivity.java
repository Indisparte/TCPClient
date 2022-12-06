package com.indisparte.clienttcp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.indisparte.clienttcp.data.network.PotholeRepository;
import com.indisparte.clienttcp.databinding.ActivityMainBinding;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    @Inject
    protected PotholeRepository mPotholeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: Close server connection");
        try {
            mPotholeRepository.closeConnection();
        } catch (IOException e) {
            Log.e(TAG, "onStop: Error on closing connection:" + e.getMessage());
            e.printStackTrace();
        }
        super.onStop();
    }
}