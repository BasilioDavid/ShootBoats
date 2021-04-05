package com.david.basilio.Dominio;

public final class GameObjectFactory {
	public static GameObject newBarco(int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser,
									  double vidaInicial, String playerID){
		return new Barco(Tipo.BARCO, positionX, positionY, rotation, gameObjectManagerUser, vidaInicial, playerID);
	}

	public static GameObject newCajita(){return null;}

}
