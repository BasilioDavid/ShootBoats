package com.david.basilio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {
    public void main() {
//        String serverName = "146.59.238.48";
        String serverName = "192.168.17.3";
        int port = 1231;
        try{
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket server = new Socket(serverName, port);

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Start");
            InputStream inFromServer = server.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Codigo de mi barco es " + in.readUTF());
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
