package com.indisparte.clienttcp.data.network;
import androidx.annotation.NonNull;
import com.indisparte.clienttcp.data.model.Pothole;
import java.io.IOException;
import java.util.List;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class PotholeRepository {
    private static final String TAG = PotholeRepository.class.getSimpleName();
    private static PotholeRepository instance;
    private TcpClient mClient;

    private PotholeRepository() {
        this.mClient = TcpClient.getInstance();
    }

    public static PotholeRepository getInstance() {
        if (instance == null)
            instance = new PotholeRepository();
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

    public void setUsername(@NonNull String username) throws IOException {
        mClient.setUsername(username);
    }

    public List<Pothole> getPotholesByRange(int range, double latitude, double longitude) throws IOException {
        return mClient.getAllPotholesByRange(range, latitude, longitude);
    }

    public List<Pothole> getAllPotholes() throws IOException {
        return mClient.getAllPotholes();
    }

    public Double getThreshold() throws IOException {
        return mClient.getThreshold();
    }

    public void addPothole(@NonNull Pothole pothole) throws IOException {
        mClient.addPothole(pothole);
    }


    public void closeConnection() throws IOException {
        mClient.closeConnection();

    }


}
