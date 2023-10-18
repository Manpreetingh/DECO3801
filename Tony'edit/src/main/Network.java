package main;

import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Network {

    private Socket client;
    private String server;
    private int port;

    //private String pos;
    private DataOutputStream out;
    protected DataInputStream in;

    public Network() {
        this.server = "127.0.0.1";
        this.port = 5555;
        try {
            System.out.println("Trying to connect...");
            this.client = new Socket(this.server, this.port);
            this.out = new DataOutputStream(client.getOutputStream());
            this.in = new DataInputStream(client.getInputStream());

            /**
             *  if (in.available() > 0) {
             *  System.out.println("Trying to read from input stream...");
             *  this.pos = this.in.readUTF();
             *  System.out.println("Received initial message: " + this.pos);
             *  }
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connect();
    }
    /**
    public String getPos() {
        return this.pos;
    }
     **/

    public void connect() {
        new Thread(() -> {
            System.out.println("Connecting to Server");
            System.out.flush();

            while (true) {
                try {
                    String serverMessage = Network.this.in.readUTF();
                    // Do something with the server message
                    System.out.println("Received from server: " + serverMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    public void send(String data) {
        try {
            this.out.writeUTF(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
