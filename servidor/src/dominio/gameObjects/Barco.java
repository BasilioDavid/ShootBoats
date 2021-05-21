package dominio.gameObjects;

import dominio.JuegoManagerUser;
import dominio.UserInputs;
import dominio.comportamientos.Moverse;
import dominio.habilidades.PowerUp;

public class Barco extends GameObject {

	private String playerID;
	private Moverse moverse;
	private double vida;
	private PowerUp powerUp;

	double velocidadX = 0, velocidadY = 0;
	float velocidadMaxima = 3f;
	float factorMovimiento = .05f; // cuanto afecta la nueva posición a la dirección actual
	float energiaUtil = .99f; // cuanta fuerza es la restante para simular la fuerza de rozamiento
	double velocidadMinimaParaParar = .1;

	public Barco(TipoDeGameObject tipoDeGameObject, int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser,
				 double vidaInicial, String playerID){
		super(tipoDeGameObject, positionX, positionY, rotation, juegoManagerUser);
		this.moverse = new Moverse(super.getPositionX(), super.getPositionY(), this.velocidadX, this.velocidadY, this.velocidadMaxima,
				this.factorMovimiento, this.energiaUtil, this.velocidadMinimaParaParar);
		this.vida = vidaInicial;
		this.playerID = playerID;
	}

	public void nuevoPowerUp(PowerUp powerUp){
		this.powerUp = powerUp;
	}

	@Override
	public void update() {
		UserInputs userInputs = super.getJuegoManagerUser().obtenerUserInputs(this.playerID);
		if (userInputs.isDoubleTouched())
			this.powerUp.usar(this);
		this.moverse.irA(userInputs.getTouchPositioX(), userInputs.getTouchPositionY());
	}

	@Override
	public void colliding(GameObject gameObjectToCompare) {
		// tiene que ver con quien está colisionando
	}
}
