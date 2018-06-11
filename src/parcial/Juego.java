/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import parcial.razas.Razas;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Juego {

    Fase fase = new Fase(1);
    Menu menu = Menu.getInstance();

    public Juego() {
    }

    public int inicioJuego(Razas j1, Razas j2) {
//        while (j1.mostrarCentro() != "null" && j2.mostrarCentro() != "null") {
            System.out.println("");
            System.out.println("    *********** FASE: " + fase.getFase() + " ***********");
            System.out.print("    Turno del Jugador 1");
            System.out.println(j1.toString());
            System.out.println(j1.mostrarCentro());
            menu.mostrar(j1,j2);
            System.out.println(j1.mostrarCentro());

            System.out.println("");
            System.out.println("    *********** FASE: " + fase.getFase() + " ***********");
            System.out.print("    Turno del Jugador 2");
            System.out.println(j2.toString());
            System.out.println(j2.mostrarCentro());
            menu.mostrar(j2,j1);
            System.out.println(j2.mostrarCentro());

            fase.setFase(fase.getFase() + 1);
//        }
        if ("null".equals(j1.mostrarCentro())) {
            return 2;
        }
        if ("null".equals(j2.mostrarCentro())) {
            return 1;
        }
        return 0;
    }
}
