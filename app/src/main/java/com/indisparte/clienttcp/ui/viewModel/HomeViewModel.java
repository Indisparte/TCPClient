package com.indisparte.clienttcp.ui.viewModel;

import android.util.Log;
import android.util.MutableBoolean;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.indisparte.clienttcp.data.network.Repository;
import com.indisparte.clienttcp.di.component.ClientApplication;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@HiltViewModel
public class HomeViewModel extends ViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    private final Repository mRepository;

    private MutableLiveData<Boolean> isConnected;
    private MutableLiveData<List<Integer>> mIntegerList;
    private MutableLiveData<Integer> mMaxValue;

    @Inject
    public HomeViewModel(Repository repository) {
        mRepository = repository;
    }

    /**
     * @return
     */
    public LiveData<Boolean> isConnected() {
        if (isConnected == null) {
            isConnected = new MutableLiveData<>();
            checkIfConnected();
        }
        return isConnected;
    }

    /**
     * @return
     */
    public LiveData<Integer> getMaxValue() {
        if (mMaxValue == null) {
            mMaxValue = new MutableLiveData<>();
            retrieveMaxValue();
        }
        return mMaxValue;
    }

    public LiveData<List<Integer>> getIntegerList() {
        if (mIntegerList == null) {
            mIntegerList = new MutableLiveData<>();
            getAllIntegerValues();
        }
        return mIntegerList;
    }

    private void getAllIntegerValues() {
        //do async operation here
        new Thread(() -> {
            //Do whatever
            try {
                mIntegerList.postValue(mRepository.getAList());
            } catch (IOException e) {
                Log.e(TAG, "getAllIntegerValues: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    private void retrieveMaxValue() {
        //do async operation here
        new Thread(() -> {
            //Do whatever
            try {
                mMaxValue.postValue(mRepository.getMax());
            } catch (IOException e) {
                Log.e(TAG, "retrieveMaxValue: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void connect() {
        //do async call here to connect
        new Thread(() -> {
            //Do whatever
            try {
                mRepository.connect();
            } catch (IOException e) {
                Log.e(TAG, "connect: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void addInteger(int integer) {
        new Thread(() -> {
            //Do whatever
            try {
                mRepository.addInteger(integer);
            } catch (IOException e) {
                Log.e(TAG, "addInteger: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void closeConnection() {
        new Thread(() -> {
            //Do whatever
            try {
                mRepository.closeConnection();
            } catch (IOException e) {
                Log.e(TAG, "closeConnection: "+e.getMessage() );
                e.printStackTrace();
            }
        }).start();
    }


    private void checkIfConnected() {
        //do async call
        new Thread(() -> {
            //Do whatever
            isConnected.postValue(mRepository.isConnect());
        }).start();
    }

    public static final ViewModelInitializer<HomeViewModel> initializer = new ViewModelInitializer<>(
            HomeViewModel.class,
            creationExtras -> {
                ClientApplication app = (ClientApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new HomeViewModel(ClientApplication.getMyRepository());
            }
    );
}
