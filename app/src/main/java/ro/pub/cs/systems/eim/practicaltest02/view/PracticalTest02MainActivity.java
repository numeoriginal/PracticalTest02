package ro.pub.cs.systems.eim.practicaltest02.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ro.pub.cs.systems.eim.practicaltest02.R;
import ro.pub.cs.systems.eim.practicaltest02.network.ClientThread;
import ro.pub.cs.systems.eim.practicaltest02.network.ServerThread;

public class PracticalTest02MainActivity extends AppCompatActivity {
    public EditText serverPortEditText;
    public EditText addressEditText;
    public EditText clientPortEditText;
    public EditText putOperation;
    public EditText putValueEditText;
    public EditText getResultText;

    public Button startServerButton;
    public Button startClientButton;
    public Button getDataButton;

    public TextView dataTextView;

    public ServerThread serverThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        serverPortEditText = findViewById(R.id.serverPortEditText);
        addressEditText = findViewById(R.id.addressEditText);
        clientPortEditText = findViewById(R.id.clientPortEditText);
        putOperation = findViewById(R.id.putOperation);
        getResultText = findViewById(R.id.getResultText);

        startServerButton = findViewById(R.id.startServerButton);
        startClientButton = findViewById(R.id.startClientButton);


        startServerButton.setOnClickListener(v -> {
            int serverPort = Integer.parseInt(serverPortEditText.getText().toString());

            serverThread = new ServerThread(serverPort);
            serverThread.start();
        });

        startClientButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString();
            int clientPort = Integer.parseInt(clientPortEditText.getText().toString());
            String Operation = putOperation.getText().toString();
            new ClientThread(address, clientPort, Operation, getResultText).start();
        });
    }
}