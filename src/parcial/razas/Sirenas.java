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
public class Sirenas implements Razas {

    private final String nombre = "Sirenas";
//    private int esmeraldas = 600, rubis = 500, perlas = 400;
    private int esmeraldas = 2000, rubis = 1500, perlas = 1000;
    private int tope1 = 10000, tope2 = 5000, tope3 = 3000;
    private final CentroMando centro = new CentroMando(100, esmeraldas, rubis, perlas, tope1, tope2, tope3);

    private AbstractFactory factory;
    private final ArrayList<Edificaciones> edificaciones = new ArrayList<>();
    private int recolecta = 0, recolecta2 = 0;
    private final ArrayList<Milicias> milicias = new ArrayList<>();
    private final ArrayList<Vehiculos> vehiculos = new ArrayList<>();

    private final HashMap<Edificaciones, Integer> edificacionesEspera = new HashMap<>();
    private final HashMap<Milicias, Integer> miliciasEspera = new HashMap<>();
    private final HashMap<Vehiculos, Integer> vehiculosEspera = new HashMap<>();

    public int getEsmeraldas() {
        return esmeraldas;
    }

    public void setEsmeraldas(int esmeraldas) {
        if (esmeraldas <= tope2) {
            this.esmeraldas = esmeraldas;
        } else {
            System.out.println("Ya no hay capacidad para guardar más recurso");
        }
    }

    public int getRubis() {
        return rubis;
    }

    public void setRubis(int rubis) {
        if (rubis <= tope2) {
            this.rubis = rubis;
        } else {
            System.out.println("Ya no hay capacidad para guardar más recurso");
        }
    }

    public int getPerlas() {
        return perlas;
    }

    public void setPerlas(int perlas) {
        if (perlas <= tope2) {
            this.perlas = perlas;
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
        if (this.esmeraldas - costor1 >= 0 && this.rubis - costor2 >= 0) {
            setEsmeraldas(this.esmeraldas - costor1);
            setRubis(this.rubis - costor2);
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
                if (this.esmeraldas - costor1 >= 0 && this.perlas - costor2 >= 0) {
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
                if (this.rubis - costor1 >= 0 && this.perlas - costor2 >= 0) {
                    setRubis(this.rubis - costor1);
                    setPerlas(this.perlas - costor2);
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
            return "Esmeraldas: " + centro.getRecurso1() + " Rubis: " + centro.getRecurso2() + " Perlas: " + centro.getRecurso3();
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
                        if (poderConstruirEdificios(300, 200)) {
                            System.out.println("    Escoger tipo de Edificación: 1. Recolectar Recurso, 2. Generar Recurso, 3. Entrenar Milicia, 4. Construir Vehículo");
                            System.out.println("        Costo: 300 esmeraldas y 200 rubís");
                            factory = FactoryProducer.getFactory(1);
                            int aux = cuatroOpciones();
                            int espera = 1;
                            int recurso = 0;
                            if (aux == 1) {
                                System.out.println(" ¿Cúal recurso quiere recolectar? ¿Recurso 1 ó Recurso 2?");
                                recurso = dosOpciones();
                                this.edificacionesEspera.put(factory.getEdificaciones(aux, 40, recurso), espera + fase);
                                System.out.println("El edificio se creará dentro de " + espera + " fase");
                            } else {
                                this.edificacionesEspera.put(factory.getEdificaciones(aux, 40, recurso), espera + fase);
                                System.out.println("El edificio se creará dentro de " + espera + " fase");
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos");
                        }
                        break;
                    case 2:
                        if (poderConstruirMilicias(300, 200)) {
                            System.out.println("    Escoger tipo de Milicia: 1. Escuadron, 2. Especialistas");
                            System.out.println("        Costo: 300 esmeraldas y 200 perlas");
                            factory = FactoryProducer.getFactory(2);
                            int aux = dosOpciones();
                            int espera = 2;
                            if (aux == 2) {
                                if (poderConstruirEspecialista()) {
                                    this.miliciasEspera.put(factory.getMilicias(aux, 30, 15), espera + fase);
                                    System.out.println("La milicia estará preparada dentro de " + espera + " fase");
                                    setEsmeraldas(this.esmeraldas - 300);
                                    setPerlas(this.perlas - 200);
                                    centro.setRecurso1(centro.getRecurso1() - 300);
                                    centro.setRecurso3(centro.getRecurso3() - 200);
                                } else {
                                    System.out.println("No se puede añadir un nuevo especialista");
                                }
                            }
                            if (aux == 1) {
                                this.miliciasEspera.put(factory.getMilicias(aux, 30, 15), espera + fase);
                                System.out.println("La milicia estará preparada dentro de " + espera + " fase");
                                setEsmeraldas(this.esmeraldas - 300);
                                setPerlas(this.perlas - 200);
                                centro.setRecurso1(centro.getRecurso1() - 300);
                                centro.setRecurso3(centro.getRecurso3() - 200);
                            }
                        } else {
                            System.out.println("No se puede construir, insuficientes recursos o no ha creado un edificio de Entrenar Milicias");
                        }
                        break;
                    case 3:
                        if (poderConstruirVehiculos(300, 200)) {
                            System.out.println("    Escoger tipo de Vehículo: 1. Primario , 2. Secundario");
                            System.out.println("        Costo: 300 rubís y 200 perlas");
                            factory = FactoryProducer.getFactory(3);
                            int aux = dosOpciones();
                            int espera = 2;
                            if (aux == 1) {
                                this.vehiculosEspera.put(factory.getVehiculos(aux, 45, 25), espera + fase);
                                System.out.println("El vehículo estará listo dentro de " + espera + " fase");
                            } else {
                                this.vehiculosEspera.put(factory.getVehiculos(aux, 35, 15), espera + fase);
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
    public boolean mejorarCentro(double mejora) {
        int recurso = (int) ((0.25 * ((tope1 + (tope1 * mejora)) + (tope2 + (tope2 * mejora)) + (tope3 + (tope3 * mejora)))) / 3);
        if (this.esmeraldas - recurso >= 0 && this.rubis - recurso >= 0 && this.perlas - recurso >= 0) {
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
            setEsmeraldas(this.esmeraldas - recurso);
            setRubis(this.rubis - recurso);
            setPerlas(this.perlas - recurso);

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
                            setEsmeraldas(this.esmeraldas + recolecta);
                            centro.setRecurso1(centro.getRecurso1() + recolecta);
                            recolecta = 0;
                            flag = 0;
                        }
                        if (((RecolectarRecurso) e).getTipo() == 2) {
                            System.out.println("    Recolectando recurso 2...");
                            setRubis(this.rubis + recolecta2);
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
                        setPerlas(this.perlas + ((GenerarRecurso) e).getRecurso());
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
    public void atacar(Object r) {
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
                        ((Razas) r).recibirAtaque(this.milicias.get(0), aux);
                    }
                } else {
                    if (this.vehiculos.isEmpty()) {
                        System.out.println("No tiene ningún vehículo construido");
                    } else {
                        ((Razas) r).recibirAtaque(this.vehiculos.get(0), aux);
                    }
                }
            }
        }
    }

    @Override
    public void recibirAtaque(Object atacador, int seleccion) {
        int enumeracion = 1;
        switch (seleccion) {
            case 1:
                if (this.edificaciones.isEmpty() && finalJuego()) {
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
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + e.getVida());
                                System.out.println("    Atacando...");
                                e.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + e.getVida());
                                if (e.getVida() < 0) {
                                    this.edificaciones.remove(e);
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                if (this.milicias.isEmpty()) {
                    System.out.println("    El contrincante no tiene milicias para atacar");
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
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + m.getVida());
                                System.out.println("    Atacando...");
                                m.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + m.getVida());
                                if (m.getVida() < 0) {
                                    this.milicias.remove(m);
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                if (this.vehiculos.isEmpty()) {
                    System.out.println("    El contrincante no tiene vehiculos para atacar");
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
                            }
                            if (atacador instanceof Vehiculos) {
                                System.out.println("Vida antes: " + v.getVida());
                                System.out.println("    Atacando...");
                                v.recibirAtaque(((Vehiculos) atacador).atacar());
                                System.out.println("Vida después: " + v.getVida());
                                if (v.getVida() < 0) {
                                    this.vehiculos.remove(v);
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public boolean finalJuego() {
        return centro.getVida() <= 0;
    }

}
