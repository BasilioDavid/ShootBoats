package com.david.basilio.Connection;

import com.david.basilio.Dominio.JuegoManagerAdmin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ActiveConnection extends Thread{

	private Socket conexionCliente;
	private JuegoManagerAdmin juegoManagerAdmin;

	public ActiveConnection(Socket client, JuegoManagerAdmin juegoManagerAdmin) throws IOException {
		super();
		this.conexionCliente = client;
		this.juegoManagerAdmin = juegoManagerAdmin;
		DataInputStream in = new DataInputStream(client.getInputStream());
		switch (in.readUTF()){
			case "Start" -> this.nuevaMaquina();
			case "Tick" -> this.gameTick();
		}
		this.conexionCliente.close();
	}

	private void nuevaMaquina(){
		try {
			String playerID = this.juegoManagerAdmin.nuevoJugador();
			DataOutputStream out = new DataOutputStream(this.conexionCliente.getOutputStream());
			out.writeUTF(playerID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void gameTick(){

	}

//	System.out.println("Just connected to " + server.getRemoteSocketAddress());
//				DataInputStream in = new DataInputStream(server.getInputStream());
//
//				System.out.println(in.readUTF());
//				DataOutputStream out = new DataOutputStream(server.getOutputStream());
//				out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
//						+ "\nGoodbye!");
//				server.close();
}
