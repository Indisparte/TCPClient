package com.indisparte.clienttcp.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.indisparte.clienttcp.R;
import com.indisparte.clienttcp.data.network.PotholeRepository;
import com.indisparte.clienttcp.databinding.FragmentHomeBinding;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private FragmentHomeBinding mBinding;
    @Inject
    protected PotholeRepository mPotholeRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.threshold.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //get threshold
                AsyncTask.execute(() -> {
                    try {
                        mPotholeRepository.getThreshold();
                    } catch (IOException e) {
                        Log.e(TAG, "onClick: Getting threashold error, " + e.getMessage());
                    }

                });

                Log.d(TAG, "onClick: Successfully get threshold");
            }
        });
        mBinding.holelist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //get hole list
                AsyncTask.execute(() -> {
                    try {
                        mPotholeRepository.getAllPotholes();
                    } catch (IOException e) {
                        Log.e(TAG, "onClick: Error getting all potholes, " + e.getMessage());
                    }
                });
                Log.d(TAG, "onClick: Successfully get all potholes");
            }
        });
        mBinding.exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit
                AsyncTask.execute(() -> {
                    try {
                        mPotholeRepository.closeConnection();
                    } catch (IOException e) {
                        Log.e(TAG, "onClick: Error closing connection, " + e.getMessage());
                    }
                });

                Log.d(TAG, "onClick: Connection closed successfully");
            }
        });
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}