package com.david.basilio.Dominio;

public interface GameObjectManagerUser {
	void nuevoGameObject(GameObject gameObject);
	void eliminarGameObject(GameObject gameObject);
	UserInputs obtenerUserInputs();
}
