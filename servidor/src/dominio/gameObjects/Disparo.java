package dominio.gameObjects;

import dominio.JuegoManagerUser;
import dominio.comportamientos.Moverse;

public abstract class Disparo extends GameObject {

    protected float segundosDeVida;
    protected Moverse moverse;
    protected float damage;


    public Disparo(TipoDeGameObject tipoDeGameObject, int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser,
                   float segundosDeVida, Moverse moverse, float damage) {

        super(tipoDeGameObject, positionX, positionY, rotation, juegoManagerUser);
        this.segundosDeVida = segundosDeVida;
        this.moverse = moverse;
        this.damage = damage;
    }
}
