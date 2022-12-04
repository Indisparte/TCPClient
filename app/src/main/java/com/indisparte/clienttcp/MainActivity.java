package com.indisparte.clienttcp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.indisparte.clienttcp.data.network.PotholeListener;
import com.indisparte.clienttcp.data.network.Repository;
import com.indisparte.clienttcp.data.network.TcpClient;
import com.indisparte.clienttcp.databinding.ActivityMainBinding;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements PotholeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    @Inject
    private Repository mRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectToTheServer();

        // connect to the server
        binding.send.setOnClickListener(button -> {
            final String message = binding.username.getText().toString();
            if (mRepository != null) {
                AsyncTask.execute(() -> {
                    try {

                        mRepository.setNickname(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                        connection_server_error_dialog();
                    }
                });
                Log.d(TAG, "onCreate: message send successfully");
            } else {
                Log.d(TAG, "onCreate: repository is null");
            }
            //clear edittext
            binding.username.setText("");
        });
    }

    private void connection_server_error_dialog() {
        runOnUiThread(() -> {
            // TODO: 04/12/2022 Add error dialog
            Log.e(TAG, "connection_server_error_dialog: No connection with server");
        });
    }

    private void connectToTheServer() {
//        if (Common.isConnectedToInternet(getContext())) { TODO check internet
        if (mRepository == null || !mRepository.isConnect()) {
            AsyncTask.execute(() -> {
                try {
                    mRepository.connect(this);
                } catch (IOException e) {
                    e.printStackTrace();
                    mRepository = null;
                    runOnUiThread(this::connection_server_error_dialog);
                }
            });
//            }
        }
    }


    @Override
    public void onThresholdReceived(float threshold) {

    }
}