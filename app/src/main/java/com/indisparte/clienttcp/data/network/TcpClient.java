package com.indisparte.clienttcp.data.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.indisparte.clienttcp.BuildConfig;
import com.indisparte.clienttcp.data.network.ServerCommand.CommandType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that allows connection with any server.
 *
 */
public class TcpClient {

    private static final String TAG = TcpClient.class.getSimpleName();
    public static final int SO_TIMEOUT = 6000;//Maximum wait time in milliseconds
    private static TcpClient instance = null;

    //Parameters retrieved from local.properties
    private static final String HOST_NAME = BuildConfig.SERVER_ADDRESS;
    private static final int HOST_PORT = Integer.parseInt(BuildConfig.SERVER_PORT);

    private Socket mSocket;
    private BufferedReader mReader;
    private OutputStream mStream;


    //must be private for singleton pattern
    private TcpClient() {

    }

    public static TcpClient getInstance() {
        if (instance == null)
            instance = new TcpClient();
        return instance;
    }

    /**
     * Open connection with server
     *
     * @throws IOException if there are any error
     */
    public void openConnection() throws IOException {
        mSocket = new Socket(HOST_NAME, HOST_PORT);
        mSocket.setSoTimeout(SO_TIMEOUT);
        mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        mStream = mSocket.getOutputStream();
    }

    /**
     * Close connection with server
     *
     * @throws IOException if there are any error
     */
    public void closeConnection() throws IOException {
        write(new ServerCommand(CommandType.EXIT));
        mSocket.close();
    }

    /**
     * Check if connection is open
     *
     * @return True if connection is open, false otherwise
     */
    public boolean isOpen() {
        return mSocket != null && !mSocket.isClosed();
    }


    /**
     * Write on the socket
     *
     * @param command {@link ServerCommand} that describe this type command
     * @throws IOException if there are any errors
     */
    private void write(@NonNull ServerCommand command) throws IOException {
        synchronized (mStream) {
            final String completeMsg = command.getFormattedRequest();
            mStream.write((completeMsg + "\n").getBytes());
            mStream.flush();
            Log.d(TAG, "write: " + completeMsg);
        }
    }


    /**
     * Reads any messages sent from the server to the client with a maximum wait time specifiable
     *
     * @param timeout The waiting time, can also be worth 0 in case you do not want to specify a waiting time
     * @return The message read from server, can be null
     * @throws IOException In case there are any errors
     */
    private String readLine(int timeout) throws IOException {
        synchronized (mReader) {
            int prevTimeout = mSocket.getSoTimeout();
            try {
                Log.d(TAG, "Start readLine...");
                mSocket.setSoTimeout(timeout);
                final String msg = mReader.readLine();
                if (msg != null) {
                    mSocket.setSoTimeout(prevTimeout);
                    Log.d(TAG, "Received: " + msg);
                    return msg;
                }

            } catch (SocketTimeoutException e) {
                Log.e(TAG, "ReadLine Timeout: " + e.getMessage());
                mSocket.setSoTimeout(prevTimeout);
            }
        }
        return null;
    }

    /**
     * Retrieves a list of integer from the server
     * @return A list of integers
     * @throws IOException In case there are any errors
     */
    public List<Integer> getAList() throws IOException, JSONException {
        String result;
        List<Integer> integerList = new ArrayList<>();

        write(new ServerCommand(CommandType.LIST));

        if ((result = readLine(SO_TIMEOUT)) != null) {
            //Converting jsonData string into JSON object
            try {
                JSONObject jsonObject = new JSONObject(result);
                //Getting integerList JSON array from the JSON object
                JSONArray jsonArray = jsonObject.getJSONArray("integers");//json list name
                Log.d(TAG, "jsonArray: "+jsonArray);
                //Iterating JSON array
                for (int i = 0; i < jsonArray.length(); i++) {
                    //get each value in string format
                    String jsonElem = jsonArray.getString(i);
                    Log.d(TAG, "jsonElem: " + jsonElem);
                    Integer element = new Gson().fromJson(jsonElem, Integer.class);
                    Log.d(TAG, "new integer elem: " + element);
                    integerList.add(element);

                }
            } catch (JSONException e) {
                Log.e(TAG, "getAList: " + e.getMessage());
                throw e;
            }
        }

        return integerList;
    }

    /**
     * Retrieve the maximum value available
     * @return The maximum integer, in case it does not retrieve anything will return 0
     * @throws IOException In case there are any errors
     */
    public int getMax() throws IOException {
        int max = 0;
        String result;
        write(new ServerCommand(CommandType.MAX));

        if ((result = readLine(SO_TIMEOUT)) != null) {
            max = Integer.parseInt(result);
        }

        return max;
    }

    /**
     * Send an integer to the server
     * @param integer The value to send
     * @throws IOException In case there are any errors
     */
    public void addInteger(int integer) throws IOException {
        write(new ServerCommand(CommandType.NEW_INT,String.valueOf(integer)));
    }


}
