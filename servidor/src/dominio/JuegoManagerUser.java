package dominio;

import dominio.gameObjects.GameObject;

public interface JuegoManagerUser {
	void nuevoGameObject(GameObject gameObject);
	void eliminarGameObject(GameObject gameObject);
	UserInputs obtenerUserInputs(String codigo);
}
