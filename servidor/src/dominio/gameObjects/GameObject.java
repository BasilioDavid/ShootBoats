package dominio.gameObjects;

import dominio.JuegoManagerUser;

public abstract class GameObject {
    private final TipoDeGameObject tipoDeGameObject;

    private int positionX, positionY;
    private float rotation;
    private JuegoManagerUser juegoManagerUser;


    public GameObject(TipoDeGameObject tipoDeGameObject, int positionX, int positionY, float rotation, JuegoManagerUser juegoManagerUser) {
        this.tipoDeGameObject = tipoDeGameObject;
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

    public TipoDeGameObject getTipoDeGameObject() {
        return tipoDeGameObject;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public float getRotation() {
        return rotation;
    }

    public JuegoManagerUser getJuegoManagerUser() {
        return juegoManagerUser;
    }

    public String serialize(){
        return this.tipoDeGameObject.name() + "," + this.positionX + "," + this.positionY;
    }

}
