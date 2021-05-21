package dominio;


import dominio.gameObjects.GameObject;
import dominio.gameObjects.GameObjectFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Juego implements JuegoManagerUser, JuegoManagerAdmin {

	private List<GameObject> objetosEnJuego = new ArrayList<>();
	private Map<String, GameObject> relaccionBarcoCodigo = new HashMap<>();
	private Map<String, UserInputs> relaccionBarcoUserInputs = new HashMap<>();

	@Override
	public void nuevoGameObject(GameObject gameObject) {
		this.objetosEnJuego.add(gameObject);
	}

	@Override
	public void eliminarGameObject(GameObject gameObject) {
		this.objetosEnJuego.remove(gameObject);
	}

	@Override
	public UserInputs obtenerUserInputs(String playerID) {
		return this.relaccionBarcoUserInputs.get(playerID);
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
		this.nuevoBarco(playerID);
		System.out.println("Codigo del cliente" + playerID);
		return playerID;
	}

	private void nuevoBarco(String playerID){
		GameObject nuevo = GameObjectFactory.newBarco(10, 10, 0, this, 5, playerID);
		this.nuevoGameObject(nuevo);
		this.relaccionBarcoUserInputs.put(playerID, new UserInputs());
		this.relaccionBarcoCodigo.put(playerID, nuevo);
	}

	private String generateID(){
		return String.valueOf(Math.random() * 10000000);
	}
}
