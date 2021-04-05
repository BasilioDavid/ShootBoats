package com.david.basilio.Dominio;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Juego implements GameObjectManagerUser{

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
}
