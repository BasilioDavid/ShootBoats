package com.david.basilio.Dominio;

public abstract class Disparo extends GameObject {

    protected float segundosDeVida;
    protected Movimiento movimiento;
    protected float damage;


    public Disparo(Tipo tipo, int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser,
                   float segundosDeVida, Movimiento movimiento, float damage) {

        super(tipo, positionX, positionY, rotation, gameObjectManagerUser);
        this.segundosDeVida = segundosDeVida;
        this.movimiento = movimiento;
        this.damage = damage;
    }
}
