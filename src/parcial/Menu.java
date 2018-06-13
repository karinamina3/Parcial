/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import java.util.InputMismatchException;
import java.util.Scanner;
import parcial.razas.Razas;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Menu {

    private static Menu menu;

    private Menu() {
    }

    public synchronized static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void opciones() {
        System.out.println("");
        System.out.println("--- MENÚ PRINCIPAL ---");
        System.out.println("1. Construir");
        System.out.println("2. Atacar");
        System.out.println("3. Defender");
        System.out.println("4. Mejorar Nivel del Centro de Mando");
        System.out.println("5. Recolectar Recursos");
        System.out.println("6. Generar Recursos");
        System.out.println("7. Terminar turno");
    }

    public void mostrar(Razas raza1, Razas raza2, int fase) {
        int opcion = 8;
        int flag = 1;
        Scanner leer = new Scanner(System.in);

        while (opcion != 7) {
            opciones();
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        raza1.construir(fase);
                        break;
                    case 2:
                        raza1.atacar(raza2);
                        break;
                    case 3:
//                        raza.defender();
                        break;
                    case 4:
                        System.out.println("    Mejoras disponibles: 1. Aumentar 10% la capacidad, 2. Aumentar 30% la capacidad, 3. Aumentar 50% la capacidad");
                        if (raza1.mejorarCentro(mejora())){
                            System.out.println("Mejora realizada satisfactoriamente");
                        } else {
                            System.out.println("No se pudo realizar la mejora, recursos insuficientes");
                        }
                        break;
                    case 5:                        
                        if (flag == 1){
                            raza1.recolectarRecurso(1);
                            flag = 0;
                        } else {
                            System.out.println("Solo se puede recolectar recursos una vez por fase");
                        }                        
                        break;
                    case 6:
                        raza1.generarRecurso(1);
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Por favor, ingrese una opción válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número");
                leer.nextLine();
            }
        }
    }

    public double mejora() {
        int opcion = 4;
        Scanner leer = new Scanner(System.in);

        while (opcion != 3) {
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        return 0.10;
                    case 2:
                        return 0.30;
                    case 3:
                        return 0.50;
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
