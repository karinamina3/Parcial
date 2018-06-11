/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.razas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import parcial.AbstractFactory;
import parcial.CentroMando;
import parcial.FactoryProducer;
import parcial.edificaciones.ConstruirVehiculos;
import parcial.edificaciones.Edificaciones;
import parcial.edificaciones.EntrenarMilicias;
import parcial.edificaciones.GenerarRecurso;
import parcial.milicias.Especialistas;
import parcial.milicias.Milicias;
import parcial.vehiculos.Vehiculos;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Hadas implements Razas {

    private final String nombre = "Hadas";
    int flag = 1;

    private int polvosHadas = 100, estrellas = 80, pociones = 60;
    private final int tope1 = 10000 , tope2 = 5000, tope3 = 3000;
    private final CentroMando centro = new CentroMando(100, polvosHadas, estrellas, pociones, tope1, tope2, tope3);

    private AbstractFactory factory;
    private final ArrayList<Edificaciones> edificaciones = new ArrayList<>();
    private final ArrayList<Milicias> milicias = new ArrayList<>();
    private final ArrayList<Vehiculos> vehiculos = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public int getPolvosHadas() {
        return polvosHadas;
    }

    public void setPolvosHadas(int polvosHadas) {
        this.polvosHadas = polvosHadas;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getPociones() {
        return pociones;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }

//    public ArrayList<Edificaciones> getEdificaciones() {
//        return edificaciones;
//    }
//
//    public ArrayList<Milicias> getMilicias() {
//        return milicias;
//    }
//
//    public ArrayList<Vehiculos> getVehiculos() {
//        return vehiculos;
//    }
    public void addEdificaciones(Edificaciones edificacion) {
        this.edificaciones.add(edificacion);
    }

    public void addMilicias(Milicias milicia) {
        this.milicias.add(milicia);
    }

    public void addVehiculos(Vehiculos vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    @Override
    public void mostrar() {
        System.out.println("Total de elementos creados:");
        edificaciones.forEach((e) -> {
            System.out.println("    Edificaciones: " + e.toString());
        });
        milicias.forEach((m) -> {
            System.out.println("    Milicias: " + m.toString());
        });
        vehiculos.forEach((v) -> {
            System.out.println("    Vehículos: " + v.toString());
        });
    }

    @Override
    public boolean poderConstruirEdificios(int costor1, int costor2) {
        if (this.polvosHadas - costor1 >= 0 && this.estrellas - costor2 >= 0) {
            setPolvosHadas(this.polvosHadas - costor1);
            setEstrellas(this.estrellas - costor2);
            centro.setRecurso1(centro.getRecurso1() - costor1);
            centro.setRecurso2(centro.getRecurso2() - costor2);
            return true;
        }
        return false;
    }

    @Override
    public boolean poderConstruirMilicias(int costor1, int costor2) {
        for (Edificaciones edificacion : edificaciones) {
            if (edificacion instanceof EntrenarMilicias) {
                if (this.polvosHadas - costor1 >= 0 && this.pociones - costor2 >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean poderConstruirEspecialista() {
        for (Milicias milicia : milicias) {
            if (milicia instanceof Especialistas) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean poderConstruirVehiculos(int costor1, int costor2) {
        for (Edificaciones edificacion : edificaciones) {
            if (edificacion instanceof ConstruirVehiculos) {
                if (this.estrellas - costor1 >= 0 && this.pociones - costor2 >= 0) {
                    setEstrellas(this.estrellas - costor1);
                    setPociones(this.pociones - costor2);
                    centro.setRecurso2(centro.getRecurso2() - costor1);
                    centro.setRecurso3(centro.getRecurso3() - costor2);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String mostrarCentro() {
        if (centro.getVida() > 0) {
            return "Polvos Hadas: " + centro.getRecurso1() + " Estrellas: " + centro.getRecurso2() + " Pociones: " + centro.getRecurso3();
        }
        return "null";
    }

    @Override
    public String toString() {
        return ": " + nombre;
    }

    @Override
    public void construir() {
        int opcion = 5;
        Scanner leer = new Scanner(System.in);

        while (opcion != 4) {
            opcionesConstruir();
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        if (poderConstruirEdificios(50, 30)) {
                            System.out.println("    Escoger tipo de Edificación: 1. Recolectar Recurso, 2. Generar Recurso, 3. Entrenar Milicia, 4. Construir Vehículo");
                            factory = FactoryProducer.getFactory(1);
                            addEdificaciones(factory.getEdificaciones(construirEdificaciones(), 35, 1));
//                        mostrar();
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos");
                        }
                        break;
                    case 2:
                        if (poderConstruirMilicias(50, 30)) {
                            System.out.println("    Escoger tipo de Milicia: 1. Escuadron, 2. Especialistas");
                            factory = FactoryProducer.getFactory(2);
                            int aux = construirMiliciasVehiculos();
                            if (aux == 2) {
                                if (poderConstruirEspecialista()) {
                                    addMilicias(factory.getMilicias(aux, 40, 3, 10));
                                    setPolvosHadas(this.polvosHadas - 50);
                                    setPociones(this.pociones - 30);
                                    centro.setRecurso1(centro.getRecurso1() - 50);
                                    centro.setRecurso3(centro.getRecurso3() - 30);
                                } else {
                                    System.out.println("No se puede añadir un nuevo especialista");
//                                mostrar();                                
                                }
                            }
                            if (aux == 1) {
                                addMilicias(factory.getMilicias(aux, 40, 3, 10));
                                setPolvosHadas(this.polvosHadas - 50);
                                setPociones(this.pociones - 30);
                                centro.setRecurso1(centro.getRecurso1() - 50);
                                centro.setRecurso3(centro.getRecurso3() - 30);
//                                mostrar();                                
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos o no ha creado un edificio de entranar milicias");
                        }
                        break;
                    case 3:
                        if (poderConstruirVehiculos(50, 30)) {
                            System.out.println("    Escoger tipo de Vehículo: 1. Tipo 1, 2. Tipo 2");
                            factory = FactoryProducer.getFactory(3);
                            addVehiculos(factory.getVehiculos(construirMiliciasVehiculos(), 30, 2, 10));
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos o no ha creado un edificio de construir vehículos");
                        }
                        break;
                    case 4:
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

    @Override
    public void opcionesConstruir() {
        System.out.println("");
        System.out.println("--- Menú Construir ---");
        System.out.println("1. Edificaciones");
        System.out.println("2. Milicias");
        System.out.println("3. Vehículos");
        System.out.println("4. Regresar al menú principal");
    }

    @Override
    public int construirEdificaciones() {
        int opcion = 5;
        Scanner leer = new Scanner(System.in);

        while (opcion != 4) {
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
                        return 4;
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

    @Override
    public int construirMiliciasVehiculos() {
        int opcion = 3;
        Scanner leer = new Scanner(System.in);

        while (opcion != 2) {
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
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

    @Override
    public boolean mejorarCentro(double mejora) {
        int recurso = (int) ((0.25 *((tope1 + (tope1 * mejora)) + (tope2 + (tope2 * mejora)) + (tope3 + (tope3 * mejora)))) / 3);        
        if (this.polvosHadas - recurso >= 0 && this.estrellas - recurso >= 0 && this.pociones - recurso >= 0){
            int t1 = (int) (tope1 + (tope1 * 0.10));
            int t2 = (int) (tope2 + (tope2 * 0.10));
            int t3 = (int) (tope3 + (tope3 * 0.10));        
            centro.setTr1(t1);
            centro.setTr2(t2);
            centro.setTr3(t3);
            setPolvosHadas(this.polvosHadas - recurso);
            setEstrellas(this.estrellas - recurso);
            setPociones(this.pociones - recurso);
            centro.setRecurso1(centro.getRecurso1() - recurso);
            centro.setRecurso2(centro.getRecurso2() - recurso);
            centro.setRecurso3(centro.getRecurso3() - recurso);    
            return true;
        }
        return false;
    }

    @Override
    public void atacar(Object r) {
        System.out.println(r.toString());
    }
    
    @Override
    public void generarRecurso() {
        if (edificaciones.size() == 0) {
            System.out.println("Cree la edificación antes");
        } else {
            if (flag == 0) {
                System.out.println("Solo se puede generar recurso una vez por fase");
            }
            if (flag == 1) {
                for (Edificaciones e : edificaciones) {
                    if (e instanceof GenerarRecurso) {
//                        System.out.println("Recurso 3 antes: " + getPociones());
                        System.out.println("    Generando recurso 3...");
                        setPociones(this.pociones + ((GenerarRecurso) e).getRecurso());
                        centro.setRecurso3(centro.getRecurso3() + ((GenerarRecurso) e).getRecurso());
//                        System.out.println("Recurso 3 después: " + getPociones());
                        flag = 0;
                    } else {
                        System.out.println("No se puede generar recurso, cree una edificación generar recurso antes");
                    }
                }
            }
        }
    }
    
}
