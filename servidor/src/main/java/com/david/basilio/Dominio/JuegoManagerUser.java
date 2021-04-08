package com.david.basilio.Dominio;

public interface JuegoManagerUser {
	void nuevoGameObject(GameObject gameObject);
	void eliminarGameObject(GameObject gameObject);
	UserInputs obtenerUserInputs();
}
