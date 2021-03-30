package com.david.basilio.Dominio;

public class Movimiento {
	int posicionX, posicionY;
	double velocidadX, velocidadY;
	float velocidadMaxima;
	float factorMovimiento; // cuanto afecta la nueva posición a la dirección actual
	float energiaUtil; // cuanta fuerza es la restante para simular la fuerza de rozamiento
	double velocidadMinimaParaParar;


	public Movimiento(int posicionInicialX, int posicionInicialY, double velocidadInicialX, double velocidadInicialY,
					  float velocidadMaxima, float factorMovimiento, float energiaUtil, double velocidadMinimaParaParar){
		this.posicionX = posicionInicialX;
		this.posicionY = posicionInicialY;
		this.velocidadX = velocidadInicialX;
		this.velocidadY = velocidadInicialY;
		this.velocidadMaxima = velocidadMaxima;
		this.factorMovimiento = factorMovimiento;
		this.energiaUtil = energiaUtil;
		this.velocidadMinimaParaParar = velocidadMinimaParaParar;
	}

	public void irA(int posicionObjetivoX, int posicionObjetivoY){
		posicionObjetivoY = 1000 - posicionObjetivoY; // en vez de poner 1000 a pelo tendría que pedir el tamaño de la pantalla
		int diferenciaX = posicionObjetivoX - this.posicionX;
		int diferenciaY = posicionObjetivoY - this.posicionY;

		double velocidad = this.calcularVelocidad(diferenciaX, diferenciaY);

		if (velocidad > this.velocidadMaxima){
			diferenciaX = (int) (this.velocidadMaxima * diferenciaX / velocidad);
			diferenciaY = (int) (this.velocidadMaxima * diferenciaY / velocidad);
		}

		this.velocidadY = (diferenciaY * this.factorMovimiento + this.velocidadY);
		this.velocidadX = (diferenciaX * this.factorMovimiento + this.velocidadX);

		this.mover(true);
	}

	/**
	 * Método que calcula la velocidad que lleva el objeto.
	 * Para esto sacamos la hipotenusa del triangulo formado a partir de la diferencia entre la posición actual y la posición objetivo
	 * @param velocidadX velocidad en x
	 * @param velocidadY velocidad en y
	 * @return la velocidad
	 */
	private double calcularVelocidad(int velocidadX, int velocidadY){
		return Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);
	}

	public void mover(boolean enableResistence){
		if (enableResistence){

			float energiaUtilX = this.energiaUtil;
			float energiaUtilY = this.energiaUtil;

			if (this.velocidadMinimaParaParar > this.velocidadY && this.velocidadY > -1 * this.velocidadMinimaParaParar)
				energiaUtilY = 0;
			if (this.velocidadMinimaParaParar > this.velocidadX && this.velocidadX > -1 * velocidadMinimaParaParar)
				energiaUtilX = 0;

			this.velocidadX *= energiaUtilX;
			this.velocidadY *= energiaUtilY;
		}

		this.posicionX += this.velocidadX;
		this.posicionY += this.velocidadY;
	}

}