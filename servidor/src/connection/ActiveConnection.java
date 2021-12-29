package connection;

import dominio.Controller;
import dominio.JuegoManagerAdmin;
import souts.Souter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Clase que maneja una petición con un cliente en hilo concreto
 * Esta clase siempre estará en uso hasta que el cliente falle, cierre la conexion o el controlador diga que se cierre
 * @author Basilio David Gómez Fernández
 */
public class ActiveConnection extends Thread{

	private Socket cliente;
	private JuegoManagerAdmin juegoManagerAdmin;
	private DataInputStream input;
	private DataOutputStream output;
	private AtomicBoolean crashGlobal;

	public ActiveConnection(Socket client, JuegoManagerAdmin juegoManagerAdmin, AtomicBoolean crashGlobal) throws IOException {
		super();
		this.crashGlobal = crashGlobal;
		this.cliente = client;
		this.juegoManagerAdmin = juegoManagerAdmin;
		try{
			this.cargarIO();
			this.esperarYManejarPeticion();
		} catch (IOException e) {
			Souter.log(Souter.CODE_WARNING, "Conexion con el cliente " + this.cliente.getInetAddress() + " ha fallado");
			this.cliente.close();
		}
	}

	public void cargarIO() throws IOException {
		this.input = new DataInputStream(this.cliente.getInputStream());
		this.output = new DataOutputStream(this.cliente.getOutputStream());
	}

	public void esperarYManejarPeticion() throws IOException {
		while (!this.crashGlobal.get()){
			switch (input.readUTF()){
				case "Start" -> this.nuevaMaquina();
				case "Tick" -> this.gameTick();
				case "ForceClose" -> this.closeAll();
			}
		}
		this.cerrarConexion();
	}

	private void cerrarConexion() throws IOException {
		this.cliente.close();
	}

	private void nuevaMaquina() throws IOException {
		String playerID = this.juegoManagerAdmin.nuevoJugador();
		DataOutputStream out = new DataOutputStream(this.cliente.getOutputStream());
		out.writeUTF(playerID);
	}

	/**
	 * Método que gestiona un tick de juego en una maquina
	 * Primero el cliente da su "UserInputs" serializado
	 * Luego el servidor envía la información de todos los objetos a el cliente
	 * Tras eso envía la información de la entrada del usuario al controlador de Juego para procesar la información
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
		Souter.log(Souter.CODE_WARNING, "El usuario " + this.cliente.getInetAddress() + " está cerrando el servidor");
		Souter.log(Souter.CODE_ERROR, "Cerrando el juego en modo DEBUG");
		Souter.log(Souter.CODE_WARNING, "Cerrando el juego en modo DEBUG");
		this.crashGlobal.set(true);
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
