package dominio.gameObjects;

import dominio.JuegoManagerUser;

public final class GameObjectDirector {
	public static GameObject newBarco(int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser,
									  double vidaInicial, String playerID){
		return new Barco(TipoDeGameObject.BARCO, positionX, positionY, rotation, juegoManagerUser, vidaInicial, playerID);
	}

	public static GameObject newCajita(){return null;}

}
