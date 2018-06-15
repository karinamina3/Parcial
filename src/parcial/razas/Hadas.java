/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.razas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import parcial.AbstractFactory;
import parcial.CentroMando;
import parcial.FactoryProducer;
import parcial.edificaciones.ConstruirVehiculos;
import parcial.edificaciones.Edificaciones;
import parcial.edificaciones.EntrenarMilicias;
import parcial.edificaciones.GenerarRecurso;
import parcial.edificaciones.RecolectarRecurso;
import parcial.milicias.Especialistas;
import parcial.milicias.Milicias;
import parcial.vehiculos.Vehiculos;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Hadas implements Razas {

    private final String nombre = "Hadas";
//    private int polvosHadas = 500, estrellas = 400, pociones = 300;       //poder hacer solamente 2 construcciones
    private int polvosHadas = 2000, estrellas = 1500, pociones = 1000;
    private int tope1 = 10000, tope2 = 5000, tope3 = 3000;
    private final CentroMando centro = new CentroMando(100, polvosHadas, estrellas, pociones, tope1, tope2, tope3);

    private AbstractFactory factory;
    private final ArrayList<Edificaciones> edificaciones = new ArrayList<>();
    private int recolecta = 0, recolecta2 = 0;
    private final ArrayList<Milicias> milicias = new ArrayList<>();
    private final ArrayList<Vehiculos> vehiculos = new ArrayList<>();

    private final HashMap<Edificaciones, Integer> edificacionesEspera = new HashMap<>();
    private final HashMap<Milicias, Integer> miliciasEspera = new HashMap<>();
    private final HashMap<Vehiculos, Integer> vehiculosEspera = new HashMap<>();

    public int getPolvosHadas() {
        return polvosHadas;
    }

    public void setPolvosHadas(int polvosHadas) {
        if (polvosHadas <= tope2) {
            this.polvosHadas = polvosHadas;
        } else {
            System.out.println("Ya no hay capacidad para guardar más recurso");
        }
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        if (estrellas <= tope2) {
            this.estrellas = estrellas;
        } else {
            System.out.println("Ya no hay capacidad para guardar más recurso");
        }
    }

    public int getPociones() {
        return pociones;
    }

    public void setPociones(int pociones) {
        if (pociones <= tope2) {
            this.pociones = pociones;
        } else {
            System.out.println("Ya no hay capacidad para guardar más recurso");
        }
    }

    public void setTope1(int tope1) {
        this.tope1 = tope1;
    }

    public void setTope2(int tope2) {
        this.tope2 = tope2;
    }

    public void setTope3(int tope3) {
        this.tope3 = tope3;
    }

    @Override
    public void mostrar() {
        System.out.println("");
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
    public boolean poderConstruirEspecialistaAux() {
        for (Iterator<Map.Entry<Milicias, Integer>> it = miliciasEspera.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Milicias, Integer> x = it.next();
            if (x.getKey() instanceof Especialistas) {
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
        } else {
            return "fin";
        }
    }

    @Override
    public String toString() {
        return ": " + nombre;
    }

    @Override
    public void construir(int fase) {
        int opcion = 5;
        Scanner leer = new Scanner(System.in);

        while (opcion != 4) {
            opcionesConstruir();
            System.out.print(": ");
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        if (poderConstruirEdificios(250, 150)) {
                            System.out.println("    Escoger tipo de Edificación: 1. Recolectar Recurso, 2. Generar Recurso, 3. Entrenar Milicia, 4. Construir Vehículo");
                            System.out.println("        Costo: 250 polvos de hadas y 150 estrellas, Vida: 35");
                            factory = FactoryProducer.getFactory(1);
                            int aux = cuatroOpciones();
                            int espera = 1;
                            int recurso = 0;
                            if (aux == 1) {
                                System.out.println(" ¿Cúal recurso quiere recolectar? ¿Recurso 1 ó Recurso 2?");
                                recurso = dosOpciones();
                                this.edificacionesEspera.put(factory.getEdificaciones(aux, 35, recurso), espera + fase);
                                System.out.println("El edificio se creará dentro de " + espera + " fase");
                            } else {
                                this.edificacionesEspera.put(factory.getEdificaciones(aux, 35, recurso), espera + fase);
                                System.out.println("El edificio se creará dentro de " + espera + " fase");
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos");
                        }
                        break;
                    case 2:
                        if (poderConstruirMilicias(250, 150)) {
                            System.out.println("    Escoger tipo de Milicia: 1. Escuadrón, 2. Especialista");
                            System.out.println("        Costo: 250 polvos de hadas y 150 pociones, Vida: 40, Ataque: 10 y 20");
                            factory = FactoryProducer.getFactory(2);
                            int aux = dosOpciones();
                            int espera = 3;
                            if (aux == 2) {
                                if (poderConstruirEspecialista() && poderConstruirEspecialistaAux()) {
                                    this.miliciasEspera.put(factory.getMilicias(aux, 40, 20), espera + fase);
                                    System.out.println("La milicia estará preparada dentro de " + espera + " fase");
                                    setPolvosHadas(this.polvosHadas - 250);
                                    setPociones(this.pociones - 150);
                                    centro.setRecurso1(centro.getRecurso1() - 250);
                                    centro.setRecurso3(centro.getRecurso3() - 150);
                                } else {
                                    System.out.println("No se puede añadir un nuevo especialista");
                                }
                            }
                            if (aux == 1) {
                                this.miliciasEspera.put(factory.getMilicias(aux, 40, 10), espera + fase);
                                System.out.println("La milicia estará preparada dentro de " + espera + " fase");
                                setPolvosHadas(this.polvosHadas - 250);
                                setPociones(this.pociones - 150);
                                centro.setRecurso1(centro.getRecurso1() - 250);
                                centro.setRecurso3(centro.getRecurso3() - 150);
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos o no ha creado un edificio de Entrenar Milicias");
                        }
                        break;
                    case 3:
                        if (poderConstruirVehiculos(250, 150)) {
                            System.out.println("    Escoger tipo de Vehículo: 1. Primario , 2. Secundario");
                            System.out.println("        Costo: 250 estrellas y 150 pociones, Vida: 40 y 30, Ataque: 20 y 10");
                            factory = FactoryProducer.getFactory(3);
                            int aux = dosOpciones();
                            int espera = 2;
                            if (aux == 1) {
                                this.vehiculosEspera.put(factory.getVehiculos(aux, 40, 20), espera + fase);
                                System.out.println("El vehículo estará listo dentro de " + espera + " fase");
                            } else {
                                this.vehiculosEspera.put(factory.getVehiculos(aux, 30, 10), espera + fase);
                                System.out.println("El vehículo estará listo dentro de " + espera + " fase");
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos o no ha creado un edificio de Construir Vehículos");
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
    public void fasesEspera(int fase) {
//        System.out.println(edificacionesEspera);
//        System.out.println(miliciasEspera);
//        System.out.println(vehiculosEspera);        

        for (Iterator<Map.Entry<Edificaciones, Integer>> it = edificacionesEspera.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Edificaciones, Integer> x = it.next();
//            System.out.println("fase: " + fase);
//            System.out.println("valor: " + x.getValue());            
            if (x.getValue() == fase) {
                this.edificaciones.add(x.getKey());
                it.remove();
            }
        }

        for (Iterator<Map.Entry<Milicias, Integer>> it = miliciasEspera.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Milicias, Integer> x = it.next();
            if (x.getValue() == fase) {
                this.milicias.add(x.getKey());
                it.remove();
            }
        }

        for (Iterator<Map.Entry<Vehiculos, Integer>> it = vehiculosEspera.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Vehiculos, Integer> x = it.next();
            if (x.getValue() == fase) {
                this.vehiculos.add(x.getKey());
                it.remove();
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
    public int cuatroOpciones() {
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
    public int tresOpciones() {
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
    public int dosOpciones() {
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
        int recurso = (int) ((0.25 * ((tope1 + (tope1 * mejora)) + (tope2 + (tope2 * mejora)) + (tope3 + (tope3 * mejora)))) / 3);
        if (this.polvosHadas - recurso >= 0 && this.estrellas - recurso >= 0 && this.pociones - recurso >= 0) {
            int t1 = (int) (tope1 + (tope1 * 0.10));
            int t2 = (int) (tope2 + (tope2 * 0.10));
            int t3 = (int) (tope3 + (tope3 * 0.10));
            centro.setTr1(t1);
            centro.setTr2(t2);
            centro.setTr3(t3);
            centro.setRecurso1(centro.getRecurso1() - recurso);
            centro.setRecurso2(centro.getRecurso2() - recurso);
            centro.setRecurso3(centro.getRecurso3() - recurso);
            setTope1(t1);
            setTope2(t2);
            setTope3(t3);
            setPolvosHadas(this.polvosHadas - recurso);
            setEstrellas(this.estrellas - recurso);
            setPociones(this.pociones - recurso);

            return true;
        }
        return false;
    }

    @Override
    public void recolectarAux() {
        for (Edificaciones e : edificaciones) {
            if (e instanceof RecolectarRecurso) {
                if (((RecolectarRecurso) e).getTipo() == 1) {
                    this.recolecta += ((RecolectarRecurso) e).getRecurso();
                }
                if (((RecolectarRecurso) e).getTipo() == 2) {
                    this.recolecta2 += ((RecolectarRecurso) e).getRecurso();
                }
            }
        }
    }

    @Override
    public void recolectarRecurso(int flag) {
//        System.out.println("1: " + recolecta);
//        System.out.println("2: " + recolecta2);

        if (edificaciones.isEmpty()) {
            System.out.println("No se puede recolectar recursos, cree una edificación Recolectar Recurso antes");
        } else {
            if (flag == 1) {
                for (Edificaciones e : edificaciones) {
                    if (e instanceof RecolectarRecurso) {
                        if (((RecolectarRecurso) e).getTipo() == 1) {
                            System.out.println("    Recolectando recurso 1...");
                            setPolvosHadas(this.polvosHadas + recolecta);
                            centro.setRecurso1(centro.getRecurso1() + recolecta);
                            recolecta = 0;
                            flag = 0;
                        }
                        if (((RecolectarRecurso) e).getTipo() == 2) {
                            System.out.println("    Recolectando recurso 2...");
                            setEstrellas(this.estrellas + recolecta2);
                            centro.setRecurso2(centro.getRecurso2() + recolecta2);
                            recolecta2 = 0;
                            flag = 0;
                        }
                    }
                }
            }
            if (flag == 1) {
                System.out.println("No se puede recolectar recursos, cree una edificación Recolectar Recurso antes");
            }
        }
    }

    @Override
    public void generarRecurso(int flag) {
        if (edificaciones.isEmpty()) {
            System.out.println("No se puede generar recursos, cree una edificación Generar Recurso antes");
        } else {
            if (flag == 1) {
                for (Edificaciones e : edificaciones) {
                    if (e instanceof GenerarRecurso) {
//                        System.out.println("Recurso 3 antes: " + getMaderas());
                        System.out.println("    Generando recurso 3...");
                        setPociones(this.pociones + ((GenerarRecurso) e).getRecurso());
                        centro.setRecurso3(centro.getRecurso3() + ((GenerarRecurso) e).getRecurso());
//                        System.out.println("Recurso 3 después: " + getMaderas());
                        flag = 0;
                    }
                }
            }
            if (flag == 1) {
                System.out.println("No se puede generar recursos, cree una edificación Generar Recurso antes");
            }
        }
    }

    @Override
    public boolean atacar(Object r) {
        if (r instanceof Razas) {  //poder acceder a los métodos
            if (this.milicias.isEmpty() && this.vehiculos.isEmpty()) {
                System.out.println("No puede atacar, cree una milicia o algún vehículo");
            } else {
                System.out.println("    Contrincante" + r.toString());
                System.out.println("¿Qué quiere atacar? 1. Edificaciones, 2. Milicas, 3. Vehículos");
                int aux = tresOpciones();
                System.out.println("    Escoge que quiere enviar a atacar: ");
                System.out.println("        1. Milicias, 2. Vehículos");
                int aux2 = dosOpciones();
                if (aux2 == 1) {
                    if (this.milicias.isEmpty()) {
                        System.out.println("No tiene ninguna milicia preparada");
                    } else {
                        if (((Razas) r).recibirAtaque(this.milicias.get(0), aux)) {
                            return true;
                        }
                    }
                } else {
                    if (this.vehiculos.isEmpty()) {
                        System.out.println("No tiene ningún vehículo construido");
                    } else {
                        if (((Razas) r).recibirAtaque(this.vehiculos.get(0), aux)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean recibirAtaque(Object atacador, int seleccion) {
        int enumeracion = 1;
        switch (seleccion) {
            case 1:
                if (this.edificaciones.isEmpty()) {
                    System.out.println("    Se atacará el Centro de Mando del contrincante, porque no tiene más edificios");
                    if (centro.getVida() > 0) {
                        if (atacador instanceof Milicias) {
                            System.out.println("Vida antes:" + centro.getVida());
                            System.out.println("    Atacando...");
                            centro.setVida(centro.getVida() - ((Milicias) atacador).atacar());
                            System.out.println("Vida después: " + centro.getVida());
                        }
                        if (atacador instanceof Vehiculos) {
                            System.out.println("Vida antes:" + centro.getVida());
                            System.out.println("    Atacando...");
                            centro.setVida(centro.getVida() - ((Vehiculos) atacador).atacar());
                            System.out.println("Vida después: " + centro.getVida());
                        }
                    }
                } else {
                    System.out.println("    Escoge que edificación quieres atacar:");
                    for (Edificaciones e : this.edificaciones) {
                        System.out.println(enumeracion + ". " + e.toString());
                        enumeracion++;
                    }
                    int opcion;
                    Scanner leer = new Scanner(System.in);

                    do {
                        try {
                            System.out.print(": ");
                            opcion = leer.nextInt();
                            if (opcion > 0 && opcion < this.edificaciones.size() + 1) {
                                break;
                            } else {
                                System.out.println("Por favor, ingrese una opción válida");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, ingrese un número");
                            leer.nextLine();
                        }
                    } while (true);

                    Edificaciones e = this.edificaciones.get(opcion - 1);

                    if (e.getVida() > 0) {
                        if (e instanceof Edificaciones) {
                            if (atacador instanceof Milicias) {
                                System.out.println("Vida antes: " + e.getVida());
                                System.out.println("    Atacando...");
                                e.recibirAtaque(((Milicias) atacador).atacar());
                                System.out.println("Vida después: " + e.getVida());
                                if (e.getVida() < 0) {
                                    this.edificaciones.remove(e);
                                }
                                return true;
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + e.getVida());
                                System.out.println("    Atacando...");
                                e.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + e.getVida());
                                if (e.getVida() < 0) {
                                    this.edificaciones.remove(e);
                                }
                                return true;
                            }
                        }
                    }
                }
                return true;
            case 2:
                if (this.milicias.isEmpty()) {
                    System.out.println("El contrincante no tiene milicias para atacar");
                } else {
                    System.out.println("    Escoge que milicia quieres atacar:");
                    for (Milicias m : this.milicias) {
                        System.out.println(enumeracion + ". " + m.toString());
                        enumeracion++;
                    }
                    int opcion;
                    Scanner leer = new Scanner(System.in);

                    do {
                        try {
                            System.out.print(": ");
                            opcion = leer.nextInt();
                            if (opcion > 0 && opcion < this.milicias.size() + 1) {
                                break;
                            } else {
                                System.out.println("Por favor, ingrese una opción válida");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, ingrese un número");
                            leer.nextLine();
                        }
                    } while (true);

                    Milicias m = this.milicias.get(opcion - 1);

                    if (m.getVida() > 0) {
                        if (m instanceof Milicias) {
                            if (atacador instanceof Milicias) {
                                System.out.println("Vida antes: " + m.getVida());
                                System.out.println("    Atacando...");
                                m.recibirAtaque(((Milicias) atacador).atacar());
                                System.out.println("Vida después: " + m.getVida());
                                if (m.getVida() < 0) {
                                    this.milicias.remove(m);
                                }
                                return true;
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + m.getVida());
                                System.out.println("    Atacando...");
                                m.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + m.getVida());
                                if (m.getVida() < 0) {
                                    this.milicias.remove(m);
                                }
                                return true;
                            }
                        }
                    }
                }
                return false;
            case 3:
                if (this.vehiculos.isEmpty()) {
                    System.out.println("El contrincante no tiene vehiculos para atacar");
                } else {
                    System.out.println("    Escoge que vehículo quieres atacar:");
                    for (Vehiculos v : this.vehiculos) {
                        System.out.println(enumeracion + ". " + v.toString());
                        enumeracion++;
                    }
                    int opcion;
                    Scanner leer = new Scanner(System.in);

                    do {
                        try {
                            System.out.print(": ");
                            opcion = leer.nextInt();
                            if (opcion > 0 && opcion < this.vehiculos.size() + 1) {
                                break;
                            } else {
                                System.out.println("Por favor, ingrese una opción válida");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, ingrese un número");
                            leer.nextLine();
                        }
                    } while (true);

                    Vehiculos v = this.vehiculos.get(opcion - 1);

                    if (v.getVida() > 0) {
                        if (v instanceof Vehiculos) {
                            if (atacador instanceof Milicias) {
                                System.out.println("Vida antes: " + v.getVida());
                                System.out.println("    Atacando...");
                                v.recibirAtaque(((Milicias) atacador).atacar());
                                System.out.println("Vida después: " + v.getVida());
                                if (v.getVida() < 0) {
                                    this.vehiculos.remove(v);
                                }
                                return true;
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + v.getVida());
                                System.out.println("    Atacando...");
                                v.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + v.getVida());
                                if (v.getVida() < 0) {
                                    this.vehiculos.remove(v);
                                }
                                return true;
                            }
                        }
                    }
                }
                return false;
        }
        return false;
    }

    @Override
    public boolean finalJuego() {
        return centro.getVida() <= 0;
    }

}
