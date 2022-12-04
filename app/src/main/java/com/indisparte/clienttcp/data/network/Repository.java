package com.indisparte.clienttcp.data.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.indisparte.clienttcp.data.model.Pothole;
import com.indisparte.clienttcp.util.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class Repository {
    private TcpClient mClient;
    private Thread mPotholeThread;
    private static final String TAG = Repository.class.getSimpleName();

    private final Consumer<PotholeListener> potholeListenerConsumer = (listener) -> {
        String message;
        try {
            while (true) {
                message = mClient.readLine();

                //TODO
                /*switch (message.charAt(0)) {
                    case Command.EXIT.value:
                        return;
                    case Command.THRESHOLD.value:
                        listener.onThresholdReceived(Float.parseFloat(message.substring(2).trim()));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + message.charAt(0));
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public boolean isConnect() {
        if (mClient != null)
            return mClient.isOpen();
        else return false;
    }

    public void connect(PotholeListener potholeListener) throws IOException {
        mClient = TcpClient.getInstance();
        mClient.openConnection();
        mPotholeThread = new Thread(() -> potholeListenerConsumer.accept(potholeListener));
        mPotholeThread.start();

    }

    public void setNickname(@NonNull String username) throws IOException {
        mClient.write(Command.SET_USERNAME, "[" + username + "]");
    }

    public List<Pothole> getAllPotholes() throws IOException {
        return getRooms(0, 0, "");
    }

    public List<Pothole> getPotholes(int from, int to) throws IOException {
        return getRooms(from, to, "");
    }

    public List<Pothole> getPotholesByRange(String range) throws IOException {
        return getRooms(0, 0, range);
    }

    private List<Pothole> getRooms(int from, int to, String name) throws IOException {
        if (mPotholeThread != null)
            try {
                mPotholeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        String msg = from + " " + to + " [" + name + "]";
        ArrayList<Pothole> list = new ArrayList<>();

        mClient.write(Command.HOLE_LIST, msg);

        int counter = Integer.parseInt(mClient.readLine(5 * 1000).substring(2).trim());

        String pothole;
        int i = 0;
        while (i < counter && (pothole = mClient.readLine(5 * 1000)) != null) {
            Log.d(TAG, "Pothole: " + pothole);
            //TODO get pothole details
           /* Scanner potholeScanner = new Scanner(pothole.substring(1));

            long id = potholeScanner.nextLong();
            long usersCount = potholeScanner.nextLong();
            int roomColor = potholeScanner.nextInt();
            String potholeName = stringInside(pothole, "[", "]");

            Room r = new Room(potholeName, id, usersCount, roomColor);
            list.add(r);
            Log.d(TAG, "Room Added: " + r);
            ++i;*/
        }
        return list;
    }

    public void getThreshold() throws IOException {
        mClient.write(Command.THRESHOLD, "");
    }

    public Pothole addPothole(Pothole pothole) throws IOException {
        /*String msg = pothole.getRoomColor() + " " + " [" + pothole.getName() + "]"; TODO build formatted message
        mClient.write(Command.NEW_HOLE, msg);

        String roomStr = mClient.readLine(10 * 1000);
        if (roomStr == null) return null;
        else
            return new Room(pothole.getName(), Long.parseLong(roomStr.substring(2).trim()), 0, pothole.getRoomColor());*/
        return null;
    }

    private void exit() throws IOException {
        mClient.write(Command.EXIT, "");
    }

    public void closeConnection() {
        try {
            mClient.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static String stringInside(String s, String left, String right) {
        Log.d(TAG, "String to split: " + s);
        if (s == null || s.length() < 3 || !s.contains(left) || !s.contains(right)) return "???";
        else return s.split(Pattern.quote(left))[1].split(Pattern.quote(right))[0];
    }
}
