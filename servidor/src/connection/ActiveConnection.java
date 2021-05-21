package connection;

import dominio.Controller;
import dominio.JuegoManagerAdmin;
import souts.Souter;

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
			case "ForceClose" -> this.closeAll();
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

	private void closeAll(){
		Souter.log(Souter.CODE_ERROR, "Cerrando el juego en modo DEBUG");
		Souter.log(Souter.CODE_WARNING, "Cerrando el juego en modo DEBUG");
		Controller.crash.set(true);
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
