package com.david.basilio.Dominio;

public final class GameObjectFactory {
	public static GameObject newBarco(int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser,
									  double vidaInicial, String playerID){
		return new Barco(Tipo.BARCO, positionX, positionY, rotation, juegoManagerUser, vidaInicial, playerID);
	}

	public static GameObject newCajita(){return null;}

}
