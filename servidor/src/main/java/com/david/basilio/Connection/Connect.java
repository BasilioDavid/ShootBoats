package com.david.basilio.Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

public class Connect extends Thread{

	private ServerSocket serverSocket;
//	private static final Map<String, FunctionalInterface>

	public Connect(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(0);
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for client on port " +
						serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
				this.esperandoComandos(client);

//				System.out.println("Just connected to " + server.getRemoteSocketAddress());
//				DataInputStream in = new DataInputStream(server.getInputStream());
//
//				System.out.println(in.readUTF());
//				DataOutputStream out = new DataOutputStream(server.getOutputStream());
//				out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
//						+ "\nGoodbye!");
//				server.close();
//
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void esperandoComandos(Socket client) throws IOException {
		DataInputStream in = new DataInputStream(client.getInputStream());
		switch (in.readUTF()){
			case "Start" -> this.nuevaMaquina(client);
			case "Tick" -> this.gameTick(client);
		}
	}

	private void nuevaMaquina(Socket client){

	}

	private void gameTick(Socket client){

	}
}
