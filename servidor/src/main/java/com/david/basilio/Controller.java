package com.david.basilio;

import com.david.basilio.Connection.Connect;
import com.david.basilio.Dominio.GameObjectFactory;
import com.david.basilio.Dominio.Juego;

import java.io.IOException;

public class Controller {
	private Juego juego;
	private Connect connect;

	public Controller() throws IOException {
		this.juego = new Juego();
		this.connect = new Connect(1231);
	}

	public String nuevoJugador(){
		this.juego.nuevoGameObject(GameObjectFactory.newBarco(10, 10, 0, this.juego, 5));
	}

}