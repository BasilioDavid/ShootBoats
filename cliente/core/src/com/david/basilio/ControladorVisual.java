package com.david.basilio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.david.basilio.pantallas.PantallaInicio;
import com.david.basilio.pantallas.PantallaJuego;

public class ControladorVisual extends Game {

    private final Screen INICIO, JUEGO;
    private final ControladorLogica controladorLogica;

    public ControladorVisual() {
        this.INICIO = new PantallaInicio();
        this.JUEGO = new PantallaJuego();
        this.controladorLogica = new ControladorLogica();
    }


    @Override
    public void create() {

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        super.dispose();
        this.INICIO.dispose();
        this.JUEGO.dispose();
    }
}
