package dominio;


import dominio.gameObjects.GameObject;
import dominio.gameObjects.GameObjectDirector;

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

	/**
	 * Método que comprueba la colision entre todos los objetos del juego
	 * El algoritmo utilizado tiene una tendencia exponencial, tema que en la fase mas early del juego no es un gran problema
	 * pero en cuanto se empiecen a agregar elementos a la escena va a ser un gran cuello de botella
	 */
	public void colliding(){
		for (int i = 0; i < this.objetosEnJuego.size(); i++){
			for (int j = i + 1; j < this.objetosEnJuego.size(); j++){
				this.objetosEnJuego.get(i).colliding(this.objetosEnJuego.get(j));
			}
		}
	}

	/**
	 * Método que crea un nuevo jugador
	 * @return El nombre del jugador creado
	 */
	@Override
	public synchronized String nuevoJugador(){
		String playerID = this.generateID();
		this.nuevoBarco(playerID);
		return playerID;
	}

	private String generateID(){
		return String.valueOf(Math.random() * 10000000);
	}

	private void nuevoBarco(String playerID){
		GameObject nuevo = GameObjectDirector.newBarco(10, 10, 0, this, 5, playerID);
		this.nuevoGameObject(nuevo);
		this.relaccionBarcoUserInputs.put(playerID, new UserInputs());
		this.relaccionBarcoCodigo.put(playerID, nuevo);
	}

	/**
	 * Método que usa el método serialize() de los GameObjects para generar una cadena de todos los objetos en juego.
	 * Si hay pocos elementos no creo que sea un gran problema de rendimiento,
	 * pero si empiezan a haber muchos elementos esta clase tiene papeletas de ser un gran cuello de botella
	 * @return Cadena separada por "," para los valores internos y con ";" para cada objeto
	 */
	public String getGameObjectsSerializados(){
		StringBuilder stringBuilder = new StringBuilder();
		for (GameObject gameObject : this.objetosEnJuego){
			stringBuilder.append(gameObject.serialize()).append(";");
		}
		return stringBuilder.toString();
	}

}
