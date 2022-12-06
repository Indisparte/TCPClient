package com.indisparte.clienttcp.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.indisparte.clienttcp.UserPreferenceManager;
import com.indisparte.clienttcp.data.network.PotholeRepository;
import com.indisparte.clienttcp.databinding.FragmentLoginBinding;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    @Inject
    protected PotholeRepository mPotholeRepository;
    private static final String TAG = LoginFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectToTheServer();

        // login into the server
        binding.send.setOnClickListener(button -> {
            final String username = binding.username.getText().toString();
            if (!TextUtils.isEmpty(username)) {
                putUsernameInPreferences(username);
                login(username);
            }
        });
    }

    private void putUsernameInPreferences(String username) {
        SharedPreferences settings = requireActivity().getSharedPreferences(
                UserPreferenceManager.USERNAME_PREF_KEY,
                MODE_PRIVATE
        );
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(username, null);
        editor.apply();
    }

    private void login(String username) {
        if (mPotholeRepository != null) {
            AsyncTask.execute(() -> {
                try {
                    Log.d(TAG, "login: Try to login");
                    mPotholeRepository.setUsername(username);

                } catch (IOException e) {
                    Log.e(TAG, "login: Connection server error: " + e.getMessage());
                    connection_server_error_dialog();
                }
                //clear edittext
                binding.username.setText("");
                Log.d(TAG, "login: username send successfully");

            });

        } else {
            Log.d(TAG, "login: repository is null");
        }

    }

    private void connection_server_error_dialog() {
        requireActivity().runOnUiThread(() -> {
            // TODO: 04/12/2022 Add error dialog
            Log.e(TAG, "connection_server_error_dialog: No connection with server");
        });
    }

    private void connectToTheServer() {
//        if (Common.isConnectedToInternet(getContext())) { TODO check internet
        if (mPotholeRepository == null || !mPotholeRepository.isConnect()) {
            AsyncTask.execute(() -> {
                try {
                    Log.d(TAG, "connectToTheServer: Try to connect to the server");
                    mPotholeRepository.connect();
                } catch (IOException e) {
                    Log.e(TAG, "connectToTheServer: Error in server connection:" + e.getMessage());
                    e.printStackTrace();
                    mPotholeRepository = null;
                    requireActivity().runOnUiThread(this::connection_server_error_dialog);
                }
            });
            checkIfUserAlreadyHaveUsername();

//            }
        }
    }

    private void checkIfUserAlreadyHaveUsername() {
        final String username = UserPreferenceManager.getInstance().getUserName();
        // check if we have the username saved in the preferences, if not, user need to insert one
        if (username != null) {
            Log.d(TAG, "checkIfUserAlreadyHaveUsername: User already have an username, so login");
            // login to the server
            login(username);
        }
        Log.d(TAG, "checkIfUserAlreadyHaveUsername: User don't have a username set");
        binding.send.setClickable(true);

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}