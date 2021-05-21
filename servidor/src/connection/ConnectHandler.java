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

	// aqui tendría que poner los callbacks para todo
	public ConnectHandler(int port, JuegoManagerAdmin juegoManagerAdmin) throws IOException {
		super();
		this.serverSocket = new ServerSocket(port);
		this.serverSocket.setSoTimeout(0);
		this.juegoManagerAdmin = juegoManagerAdmin;
	}

//	public ConnectHandler(int port, CallbackVacio error, )

	@Override
	public void run() {
		Souter.log(Souter.CODE_LOG,"Socket Iniciado");
		this.trabajando.set(true);
		while (this.trabajando.get()) {
			try {
				Souter.log(Souter.CODE_LOG, "Waiting for client on port " +
						serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
				Souter.log(Souter.CODE_LOG, "Conexion establecida con el cliente " + client.getInetAddress());
				new ActiveConnection(client, juegoManagerAdmin).start();
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
