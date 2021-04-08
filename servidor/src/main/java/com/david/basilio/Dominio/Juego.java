package com.david.basilio.Dominio;


import java.util.ArrayList;
import java.util.List;

public class Juego implements JuegoManagerUser, JuegoManagerAdmin {

	private List<GameObject> objetosEnJuego = new ArrayList<>();

	@Override
	public void nuevoGameObject(GameObject gameObject) {
		this.objetosEnJuego.add(gameObject);
	}

	@Override
	public void eliminarGameObject(GameObject gameObject) {
		this.objetosEnJuego.remove(gameObject);
	}

	@Override
	public UserInputs obtenerUserInputs() {
		return null;
	}

	public void update(){
		this.objetosEnJuego.forEach(GameObject::update);
	}

	// para optimizar el codigo abría que ordenar los objetos en dos listas, una respecto la posicion x y otra respecto la posicion y
	// para que la evolución del algoritmo no sea exponencial, sino lineal
	public void colliding(){
		for (int i = 0; i < this.objetosEnJuego.size(); i++){
			for (int j = i + 1; j < this.objetosEnJuego.size(); j++){
				this.objetosEnJuego.get(i).colliding(this.objetosEnJuego.get(j));
			}
		}
	}

	@Override
	public synchronized String nuevoJugador(){
		String playerID = this.generateID();
		this.nuevoGameObject(GameObjectFactory.newBarco(10, 10, 0, this, 5, playerID));
		System.out.println("Codigo del cliente" + playerID);
		System.out.println(this.objetosEnJuego);
		return playerID;
	}

	private String generateID(){
		return String.valueOf(Math.random() * 10000000);
	}
}
