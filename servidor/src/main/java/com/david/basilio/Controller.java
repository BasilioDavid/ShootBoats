package com.david.basilio;

import com.david.basilio.Connection.ConnectHandler;
import com.david.basilio.Dominio.Juego;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
	private Juego juego;
	private ConnectHandler connectHandler;

	public Controller() throws IOException {
		this.juego = new Juego();
		this.connectHandler = new ConnectHandler(1231, this.juego);
		this.connectHandler.start();
		Scanner scanner = new Scanner(System.in);
		int resultado;
		do {
			System.out.println("mete dato");
			resultado = scanner.nextInt();
		} while (resultado != 0);
		this.connectHandler.endWork();
	}

	public static void main(String[] args) throws IOException {
		new Controller();
	}
}