package com.indisparte.clienttcp.data.network;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    private static Repository instance;
    private TcpClient mClient;

    private Repository() {
        this.mClient = TcpClient.getInstance();
    }

    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();
        return instance;
    }

    public boolean isConnect() {
        if (mClient != null)
            return mClient.isOpen();
        else return false;
    }

    public void connect() throws IOException {
        mClient = TcpClient.getInstance();
        mClient.openConnection();
    }

    public List<Integer> getAList() throws IOException, JSONException {
        return mClient.getAList();
    }

    public int getMax() throws IOException {
        return mClient.getMax();
    }

    public void addInteger(int integer) throws IOException {
        mClient.addInteger(integer);
    }

    public void closeConnection() throws IOException {
        mClient.closeConnection();

    }


}
