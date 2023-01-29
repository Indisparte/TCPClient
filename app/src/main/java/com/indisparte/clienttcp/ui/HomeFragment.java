package com.indisparte.clienttcp.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.indisparte.clienttcp.data.network.Repository;
import com.indisparte.clienttcp.databinding.FragmentHomeBinding;
import com.indisparte.clienttcp.ui.viewModel.HomeViewModel;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private FragmentHomeBinding mBinding;
    private HomeViewModel mHomeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel = new ViewModelProvider(
                requireActivity(),
                ViewModelProvider.Factory.from(HomeViewModel.initializer)
        ).get(HomeViewModel.class);

        mHomeViewModel.connect();
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

        mBinding.max.setOnClickListener(button -> {
            // get max value
            mHomeViewModel.getMaxValue().observe(getViewLifecycleOwner(), maxValue -> Log.d(TAG, "Success, received max value: " + maxValue));
        });

        mBinding.exit.setOnClickListener(exit_btn -> {
            //exit
            mHomeViewModel.closeConnection();
        });

        mBinding.addInteger.setOnClickListener(button -> {
            final String str_int = mBinding.editText.getText().toString().trim();
            final int integer = Integer.parseInt(str_int);

            mHomeViewModel.addInteger(integer);
            //clear edittext
            mBinding.editText.setText("");
        });

        mBinding.list.setOnClickListener(button -> {

            mHomeViewModel.getIntegerList().observe(getViewLifecycleOwner(), integers -> Log.d(TAG, "Success, get all integers: " + integers));
        });
    }


    @Override
    public void onDestroyView() {
        mBinding.exit.performClick();
        mBinding = null;
        super.onDestroyView();
    }
}