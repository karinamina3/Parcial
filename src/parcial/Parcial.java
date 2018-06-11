/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import java.util.InputMismatchException;
import java.util.Scanner;
import parcial.razas.FactoryRazas;
import parcial.razas.Razas;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Parcial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Razas j1;
        Razas j2;

        System.out.println("---------- LA ISLA DE NUNCA JAMÁS: LA GUERRA ----------");
        System.out.println("    Los piratas desean apoderarse de Nunca Jamás porque cuenta con grandes minas de oro, las sirenas quieren");
        System.out.println("    ellas apoderarse para así, con la magia de la isla, tener el poder de cambiar sus aletas por pies, y las");
        System.out.println("    hadas luchan por la gran fuente de polvo de hadas que podrán tener si ellas se apoderan de la isla.");
        System.out.println("");
        System.out.println("Cada raza podrá construir cuantas edificaciones, milicias o vehículos deseen mientras tengan los recursos suficientes.");
        System.out.println("¿Quiénes serán los triunfadores?");

        System.out.println("    Escoger razas: 1. Piratas, 2. Sirenas, 3. Hadas, 4. Fortalezas de cada raza");        
        Scanner leer = new Scanner(System.in);

        System.out.print("Primer Jugador");
        j1 = FactoryRazas.getRazas(escogerRaza());
//        System.out.println(j1);

        System.out.print("Segundo Jugador");
        j2 = FactoryRazas.getRazas(escogerRaza());        
//        System.out.println(j2);

        Juego juego = new Juego();
        juego.inicioJuego(j1, j2);
//        System.out.println("El jugador ganador es: " + juego.inicioJuego(j1, j2));
    }

    public static int escogerRaza() {
        int opcion = 4;
        Scanner leer = new Scanner(System.in);

        while (opcion != 3) {
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        System.out.println("    - Raza Pirata: mayor ataques");                                                                        
                        System.out.println("    - Raza Sirenas: construcciones rápidas");                                                                       
                        System.out.println("    - Raza Hadas: costos bajos");                                               
                        break;
                    default:
                        System.out.println("Por favor, ingrese una opción válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número");
                leer.nextLine();
            }
        }
        return 0;
    }
}
