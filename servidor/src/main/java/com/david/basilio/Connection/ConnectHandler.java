package com.david.basilio.Connection;

import com.david.basilio.Dominio.JuegoManagerAdmin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectHandler extends Thread{

	private ServerSocket serverSocket;
	private JuegoManagerAdmin juegoManagerAdmin;
	private AtomicBoolean trabajando = new AtomicBoolean();

	public ConnectHandler(int port, JuegoManagerAdmin juegoManagerAdmin) throws IOException {
		super();
		this.serverSocket = new ServerSocket(port);
		this.serverSocket.setSoTimeout(0);
		this.juegoManagerAdmin = juegoManagerAdmin;
	}

	@Override
	public void run() {
		System.out.println("llego");
		this.trabajando.set(true);
		while (this.trabajando.get()) {
			try {
				System.out.println("Waiting for client on port " +
						serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
				new ActiveConnection(client, juegoManagerAdmin).start();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				System.out.println("Socket closed");
				break;
			}
		}
	}

	public void endWork() throws IOException {
		System.out.println("breaking");
		this.trabajando.set(false);
		this.serverSocket.close();
	}
}
