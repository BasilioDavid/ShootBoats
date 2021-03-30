package com.david.basilio.Dominio;

public abstract class GameObject {



    protected final Tipo tipo;

    protected int positionX, positionY;
    protected float rotation;
    protected GameObjectManagerUser gameObjectManagerUser;


    public GameObject(Tipo tipo, int positionX, int positionY, float rotation, GameObjectManagerUser gameObjectManagerUser) {
        this.tipo = tipo;
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = rotation;
        this.gameObjectManagerUser = gameObjectManagerUser;
    }

    public abstract void update();

    public abstract void colliding(GameObject gameObjectToCompare);

    public void die(){

    }
}
