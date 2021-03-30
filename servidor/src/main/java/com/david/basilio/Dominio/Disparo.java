package com.david.basilio.Dominio;

import com.david.basilio.Dominio.Comportamientos.Moverse;

public abstract class Disparo extends GameObject {

    protected float segundosDeVida;
    protected Moverse moverse;
    protected float damage;


    public Disparo(Tipo tipo, int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser,
                   float segundosDeVida, Moverse moverse, float damage) {

        super(tipo, positionX, positionY, rotation, gameObjectManagerUser);
        this.segundosDeVida = segundosDeVida;
        this.moverse = moverse;
        this.damage = damage;
    }
}
