package com.david.basilio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    private static final String SNAKE_HEAD = "snakehead.png";
    Texture texture;
    int posicionX, posicionY;
    double velocidadX = 0, velocidadY = 0;
    float velocidadMaxima = 3f;
    float factorMovimiento = .05f; // cuanto afecta la nueva posición a la dirección actual
    float energiaUtil = .99f; // cuanta fuerza es la restante para simular la fuerza de rozamiento


    public Block(int posicionInicialX, int posicionInicialY){
        this.posicionX = posicionInicialX;
        this.posicionY = posicionInicialY;
        this.texture = new Texture(Block.SNAKE_HEAD);
    }

    public void follow(int posicionObjetivoX, int posicionObjetivoY){
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

        this.move(true);
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

    public void move(boolean enableResistence){
        if (enableResistence){

            float energiaUtilX = this.energiaUtil;
            float energiaUtilY = this.energiaUtil;

            if (0.1 > this.velocidadY && this.velocidadY > -0.1)
                energiaUtilY = 0;
            if (0.1 > this.velocidadX && this.velocidadX > -0.1)
                energiaUtilX = 0;

            this.velocidadX *= energiaUtilX;
            this.velocidadY *= energiaUtilY;
        }

        this.posicionX += this.velocidadX;
        this.posicionY += this.velocidadY;
    }

    public void print(SpriteBatch s){
        s.begin();
        s.draw(this.texture, this.posicionX, this.posicionY,20,20);
        s.end();
    }

    public void dispose(){
        this.texture.dispose();
    }

}
