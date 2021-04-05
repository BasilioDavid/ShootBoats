package com.david.basilio.Dominio;

import com.david.basilio.Dominio.Comportamientos.Moverse;
import com.david.basilio.Dominio.Habilidades.PowerUp;

public class Barco extends GameObject{

	private String playerID;
	private Moverse moverse;
	private double vida;
	private PowerUp powerUp;

	double velocidadX = 0, velocidadY = 0;
	float velocidadMaxima = 3f;
	float factorMovimiento = .05f; // cuanto afecta la nueva posición a la dirección actual
	float energiaUtil = .99f; // cuanta fuerza es la restante para simular la fuerza de rozamiento
	double velocidadMinimaParaParar = .1;

	public Barco(Tipo tipo, int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser,
				 double vidaInicial, String playerID){
		super(tipo, positionX, positionY, rotation, gameObjectManagerUser);
		this.moverse = new Moverse(this.positionX, this.positionY, this.velocidadX, this.velocidadY, this.velocidadMaxima,
				this.factorMovimiento, this.energiaUtil, this.velocidadMinimaParaParar);
		this.vida = vidaInicial;
		this.playerID = playerID;
	}

	public void nuevoPowerUp(PowerUp powerUp){
		this.powerUp = powerUp;
	}

	public void usarPowerUp(){
		this.powerUp.usar(this);
	}


	@Override
	public void update() {
		// tiene que moverse
	}

	@Override
	public void colliding(GameObject gameObjectToCompare) {
		// tiene que ver con quien está colisionando
	}
}
