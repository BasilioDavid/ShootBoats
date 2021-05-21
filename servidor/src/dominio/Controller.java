package dominio;

import connection.ConnectHandler;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
	private Juego juego;
	private ConnectHandler connectHandler;
	public static AtomicBoolean crash = new AtomicBoolean(false);

	public Controller() throws IOException {
		this.juego = new Juego();
		this.connectHandler = new ConnectHandler(1231, this.juego);
		this.connectHandler.start();
		this.mainLoop();
		this.connectHandler.endWork();
	}

	private void mainLoop(){
		double tiempoInicio = System.currentTimeMillis();
		while (!crash.get()){
			if (System.currentTimeMillis() - tiempoInicio > 32) {
				this.juego.update();
				tiempoInicio = System.currentTimeMillis();
			}
		}
	}



	public static void main(String[] args) throws IOException {
		new Controller();
	}
}