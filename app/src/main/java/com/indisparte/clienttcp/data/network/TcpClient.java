package com.indisparte.clienttcp.data.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.indisparte.clienttcp.BuildConfig;
import com.indisparte.clienttcp.util.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class TcpClient {
    private static final String TAG = TcpClient.class.getSimpleName();
    private static TcpClient INSTANCE = null;
    private static final String HOST_NAME = BuildConfig.SERVER_ADDRESS;
    private static final int HOST_PORT = Integer.parseInt(BuildConfig.SERVER_PORT);
    private Socket mSocket;
    private BufferedReader mReader;
    private OutputStream mStream;


    private TcpClient() {
    }

    public static TcpClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TcpClient();
        return INSTANCE;
    }

    public void openConnection() throws IOException {
        mSocket = new Socket(HOST_NAME, HOST_PORT);
        mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        mStream = mSocket.getOutputStream();
    }

    public void closeConnection() throws IOException {
        mSocket.close();
    }

    public boolean isOpen() {
        return mSocket != null && !mSocket.isClosed();
    }

    public void write(@NonNull Command command, @NonNull String msg) throws IOException {
        synchronized (mStream) {
            final String complete_msg = command.value + " " + msg;
            mStream.write((complete_msg + "\n").getBytes());
            mStream.flush();

            Log.d(TAG, "write: Sending: " + complete_msg);
        }
    }

    public String readLine() throws IOException {
        return readLine(0);
    }

    public String readLine(int timeout) throws IOException {
        synchronized (mReader) {
            int prevTimeout = mSocket.getSoTimeout();
            try {
                Log.d(TAG, "readLine: Start readLine");
                mSocket.setSoTimeout(timeout);
                final String msg = mReader.readLine();
                if (msg != null) {
                    mSocket.setSoTimeout(prevTimeout);
                    Log.d(TAG, "readLine: Received: " + msg);
                    return msg;
                }

            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                Log.d(TAG, "readLine: Timeout");
                mSocket.setSoTimeout(prevTimeout);
                throw e;
            }
        }
        return null;
    }
}
