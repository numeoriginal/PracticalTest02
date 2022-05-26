package ro.pub.cs.systems.eim.practicaltest02.network;


import static ro.pub.cs.systems.eim.practicaltest02.general.Constants.LOG_TAG;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class CommunicationThread extends Thread {
    private ServerThread serverThread;
    private Socket socket;

    public CommunicationThread(ServerThread serverThread, Socket socket) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    @Override
    public void run() {
        Log.d(LOG_TAG, "CommunicationThread started");

        if (socket == null) {
            Log.e(LOG_TAG, "Error: socket is null");
            return;
        }

        try {
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);

            String request = bufferedReader.readLine();
            Log.d(LOG_TAG, "Message received: " + request);

            String[] infos = request.split(",");

            String operation = infos[0];
            Integer op1 = Integer.parseInt(infos[1]);
            Integer op2 = Integer.parseInt(infos[2]);

            if (operation.equals("add")) {

                Integer result = op1 + op2;
                printWriter.println(result.toString());

            } else if (operation.equals("mul")) {
                try {
                    Thread.sleep(10000);

                    Integer result = op1 * op2;
                    printWriter.println(result.toString());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
