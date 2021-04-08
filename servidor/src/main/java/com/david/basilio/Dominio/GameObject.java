package com.david.basilio.Dominio;

public abstract class GameObject {



    protected final Tipo tipo;

    protected int positionX, positionY;
    protected float rotation;
    protected JuegoManagerUser juegoManagerUser;


    public GameObject(Tipo tipo, int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser) {
        this.tipo = tipo;
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = rotation;
        this.juegoManagerUser = juegoManagerUser;
    }

    public abstract void update();

    public abstract void colliding(GameObject gameObjectToCompare);

    public void die(){
        this.juegoManagerUser.eliminarGameObject(this);
    }
}
