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
import com.indisparte.clienttcp.data.model.Pothole;
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

        mBinding.threshold.setOnClickListener(threshold_btn -> {
            // get threshold
            AsyncTask.execute(() -> {
                try {
                    mPotholeRepository.getThreshold();
                    Log.d(TAG, "Success, received threshold");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Getting threshold error, " + e.getMessage());
                }

            });
        });
        mBinding.holelist.setOnClickListener(holeList_btn -> {
            //get hole list
            AsyncTask.execute(() -> {
                try {
                    mPotholeRepository.getAllPotholes();
                    Log.d(TAG, "Success, received all potholes");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Error getting all potholes, " + e.getMessage());
                }
            });
        });
        mBinding.exit.setOnClickListener(exit_btn -> {
            //exit
            AsyncTask.execute(() -> {
                try {
                    mPotholeRepository.closeConnection();
                    Log.d(TAG, "Success, connection closed");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Error closing connection, " + e.getMessage());
                }
            });
        });

        mBinding.addNewHole.setOnClickListener(addNewHole_btn -> {
            final Pothole newPothole = buildAPothole();
            //adding new hole
            AsyncTask.execute(() -> {
                try {
                    mPotholeRepository.addPothole(newPothole);
                    Log.d(TAG, "Success, new pothole added");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Error adding new pothole, " + e.getMessage());
                }
            });
        });

        mBinding.getHolesByRadius.setOnClickListener(getHolesByRadius_btn -> {
            int radius = mBinding.radius.getProgress();
            //getting holes by radius
            AsyncTask.execute(() -> {
                try {
                    mPotholeRepository.getPotholesByRange(radius,55.0,11.0);
                    Log.d(TAG, "Success, get all potholes by range");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Error getting by range, " + e.getMessage());
                }
            });
        });
    }

    private Pothole buildAPothole() {
        double latitude = Double.parseDouble(mBinding.latitude.getText().toString().trim());
        double longitude = Double.parseDouble(mBinding.longitude.getText().toString().trim());
        double variation = Double.parseDouble(mBinding.variation.getText().toString().trim());
        return new Pothole(latitude, longitude, variation);
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}