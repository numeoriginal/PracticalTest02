package ro.pub.cs.systems.eim.practicaltest02.network;

import static ro.pub.cs.systems.eim.practicaltest02.general.Constants.LOG_TAG;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class ClientThread extends Thread {
    private String address;
    private int port;
    private String value;

    private Socket socket;
    TextView responseTextView;

    public ClientThread(String address, int port, String value, TextView responseTextView) {
        this.address = address;
        this.port = port;
        this.value = value;
        this.responseTextView = responseTextView;
    }

    public void run() {
        try {
            socket = new Socket(address, port);
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);

            Log.d(LOG_TAG, "Sending value: " + value);

            printWriter.println(value);
            printWriter.flush();


            String response = bufferedReader.readLine();
            responseTextView.post(new Runnable() {
                @Override
                public void run() {
                    responseTextView.setText(response);
                }
            });

            /*String weatherInformation;

            while ((weatherInformation = bufferedReader.readLine()) != null) {
                String finalWeatherInformation = weatherInformation;
                textView.post(() -> textView.setText(finalWeatherInformation));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
