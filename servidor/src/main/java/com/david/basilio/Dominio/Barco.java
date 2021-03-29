package com.david.basilio.Dominio;

import com.david.basilio.Dominio.Habilidades.PowerUp;

public class Barco extends GameObject{
	private Movimiento movimiento;
	private double vida;
	private PowerUp powerUp;

	double velocidadX = 0, velocidadY = 0;
	float velocidadMaxima = 3f;
	float factorMovimiento = .05f; // cuanto afecta la nueva posición a la dirección actual
	float energiaUtil = .99f; // cuanta fuerza es la restante para simular la fuerza de rozamiento
	double velocidadMinimaParaParar = .1;

	public Barco(int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser, GameObject.Tipo tipo, double vidaInicial){
		super(positionX, positionY, rotation, gameObjectManagerUser, tipo);
		this.movimiento = new Movimiento(this.posicionX, this.posicionY, this.velocidadX, this.velocidadY, this.velocidadMaxima,
				this.factorMovimiento, this.energiaUtil, this.velocidadMinimaParaParar);
		this.vida = vidaInicial;
	}


	public void nuevoPowerUp(PowerUp powerUp){
		this.powerUp = powerUp;
	}

	public void usarPowerUp(){
		this.powerUp.usar(this);
	}



}
