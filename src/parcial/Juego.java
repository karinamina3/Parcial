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

    public int fase = 1;
    Menu menu = Menu.getInstance();

    public Juego() {
    }

    public void inicioJuego(Razas j1, Razas j2) {
        while (true) {
            System.out.println("");
            System.out.println("    *********** FASE: " + fase + " ***********");
            System.out.print("    Turno del Jugador 1");
            System.out.println(j1.toString());
            System.out.println(j1.mostrarCentro());
            j1.fasesEspera(fase);
            j1.recolectarAux();

            menu.mostrar(j1, j2, fase);
            System.out.println(j1.mostrarCentro());
            j1.mostrar();

            if (j2.finalJuego()) {
                System.err.println("______________Ganador: Jugador 1______________");
                break;
            }

            System.out.println("");
            System.out.println("    *********** FASE: " + fase + " ***********");
            System.out.print("    Turno del Jugador 2");
            System.out.println(j2.toString());
            System.out.println(j2.mostrarCentro());
            j2.fasesEspera(fase);
            j2.recolectarAux();

            menu.mostrar(j2, j1, fase);
            System.out.println(j2.mostrarCentro());
            j2.mostrar();

            if (j1.finalJuego()) {
                System.err.println("______________Ganador: Jugador 2______________");
                break;
            }

            fase = fase + 1;
        }
    }
}
