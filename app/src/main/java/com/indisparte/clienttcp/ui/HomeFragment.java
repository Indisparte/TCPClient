package com.indisparte.clienttcp.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.indisparte.clienttcp.data.network.Repository;
import com.indisparte.clienttcp.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private FragmentHomeBinding mBinding;
    @Inject
    protected Repository mRepository;

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

        mBinding.max.setOnClickListener(button -> {
            // get threshold
            AsyncTask.execute(() -> {
                try {
                    int max = mRepository.getMax();
                    Log.d(TAG, "Success, received max value: "+max);
                } catch (IOException e) {
                    Log.e(TAG, "Getting max error, " + e.getMessage());
                }

            });
        });

        mBinding.exit.setOnClickListener(exit_btn -> {
            //exit
            AsyncTask.execute(() -> {
                try {
                    mRepository.closeConnection();
                    Log.d(TAG, "Success, connection closed");
                } catch (IOException e) {
                    Log.e(TAG, "Error closing connection, " + e.getMessage());
                }
            });
        });

        mBinding.addInteger.setOnClickListener(button -> {
            final int integer = Integer.parseInt(((MaterialButton) button).getText().toString().trim());
            AsyncTask.execute(() -> {
                try {
                    mRepository.addInteger(integer);
                    Log.d(TAG, "Success, new integer added");
                } catch (IOException e) {
                    Log.e(TAG, "Error adding new integer, " + e.getMessage());
                }
            });
        });

        mBinding.list.setOnClickListener(button -> {
            AsyncTask.execute(() -> {
                try {
                    List<Integer> integers = mRepository.getAList();
                    Log.d(TAG, "Success, get all integers: " + integers);
                } catch (IOException e) {
                    Log.e(TAG, "Error getting list, " + e.getMessage());
                }
            });
        });
    }


    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}