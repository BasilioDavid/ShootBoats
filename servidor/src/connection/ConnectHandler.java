package connection;

import dominio.JuegoManagerAdmin;
import souts.Souter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectHandler extends Thread{

	private ServerSocket serverSocket;
	private JuegoManagerAdmin juegoManagerAdmin;
	private AtomicBoolean trabajando = new AtomicBoolean();
	private AtomicBoolean crashGlobal;

	/**
	 * Costructor de la clase con casi mas acoplamiento del que me gustaría
	 * En un futuro estaría bien quipar el JuegoManagerAdmin, ya que con eso casamos esta clase a un solo juego
	 * y cada cambio en en los metodo de esa clase posiblemente tenga que realizar cambios aquí
	 * @param port puerto donde va a trabaja el manejador de conexiones
	 * @param juegoManagerAdmin juego concreto sobre el que esta trabajando
	 * @throws IOException en caso de no poder abrir el socket
	 */
	public ConnectHandler(int port, JuegoManagerAdmin juegoManagerAdmin, AtomicBoolean crashGlobal) throws IOException {
		super();
		this.crashGlobal = crashGlobal;
		this.serverSocket = new ServerSocket(port);
		this.serverSocket.setSoTimeout(0);
		this.juegoManagerAdmin = juegoManagerAdmin;
	}


	@Override
	public void run() {
		Souter.log(Souter.CODE_LOG,"Socket Iniciado");
		this.trabajando.set(true);
		while (this.trabajando.get() && !this.crashGlobal.get()) {
			try {
				Souter.log(Souter.CODE_LOG, "Waiting for client on port " +
						serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
				Souter.log(Souter.CODE_LOG, "Conexion establecida con el cliente " + client.getInetAddress());
				new ActiveConnection(client, juegoManagerAdmin, this.crashGlobal).start();
			} catch (SocketTimeoutException s) {
				Souter.log(Souter.CODE_ERROR, "Socket timed out!");
				break;
			} catch (IOException e) {
				Souter.log(Souter.CODE_ERROR,"Socket closed");
				break;
			}
		}
	}

	public void endWork() throws IOException {
		Souter.log(Souter.CODE_LOG,"Cerrando Socket");
		this.trabajando.set(false);
		this.serverSocket.close();
		Souter.log(Souter.CODE_LOG,"Socket Cerrado");
	}
}
