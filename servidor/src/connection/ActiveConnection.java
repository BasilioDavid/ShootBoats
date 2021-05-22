package connection;

import dominio.Controller;
import dominio.JuegoManagerAdmin;
import souts.Souter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase que maneja las conexianes activas,
 * esta clase es invocada cada vez que se realiza una nueva conexion con un cliente
 * y muere cuando se termina la conexion con el mismo
 * @author Basilio David Gómez Fernández
 */
public class ActiveConnection extends Thread{

	private Socket conexionCliente;
	private JuegoManagerAdmin juegoManagerAdmin;

	public ActiveConnection(Socket client, JuegoManagerAdmin juegoManagerAdmin) {
		super();
		try{
			this.conexionCliente = client;
			this.juegoManagerAdmin = juegoManagerAdmin;
			DataInputStream in = new DataInputStream(client.getInputStream());
			switch (in.readUTF()){
				case "Start" -> this.nuevaMaquina();
				case "Tick" -> this.gameTick();
				case "ForceClose" -> this.closeAll();
			}
			this.conexionCliente.close();
		} catch (IOException e) {
			Souter.log(Souter.CODE_WARNING, "Conexion con el cliente " + this.conexionCliente.getInetAddress() + " ha fallado");
		}
	}

	private void nuevaMaquina() throws IOException {
		String playerID = this.juegoManagerAdmin.nuevoJugador();
		DataOutputStream out = new DataOutputStream(this.conexionCliente.getOutputStream());
		out.writeUTF(playerID);
	}

	/**
	 * Método que gestiona un tick de juego en una maquina
	 * Primero la maquina da su "UserInputs" serializador
	 * Luego envía la información de todos los objetos
	 * Tras eso envía la información de la entrada del usuario al controlador Juego
	 */
	private void gameTick(){
//		try {
			// doubleTouched, posx, posy

//		}

	}

	/**
	 * Método experimental para cerrar el servidor de manera forzosa
	 * Debería ser cambiado con una especie de comando dentro del prompt del servidor, sino cualquiera puede cerra el servidor
	 * queriendo o sin querer
	 */
	private void closeAll(){
		Souter.log(Souter.CODE_WARNING, "El usuario " + this.conexionCliente.getInetAddress() + " está cerrando el servidor");
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
