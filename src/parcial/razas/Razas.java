/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.razas;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public interface Razas<R> {

    void mostrar();

    boolean poderConstruirEdificios(int costor1, int costor2);

    boolean poderConstruirMilicias(int costor1, int costor2);

    boolean poderConstruirEspecialista();

    boolean poderConstruirVehiculos(int costor1, int costor2);

    String mostrarCentro();

    void construir(int fase);

    void fasesEspera(int fase);

    void opcionesConstruir();

    int cuatroOpciones();

    int tresOpciones();

    int dosOpciones();

    boolean mejorarCentro(double mejora);

    void recolectarAux();

    void recolectarRecurso(int flag);

    void generarRecurso(int flag);

    void atacar(R r);

    void recibirAtaque(R atacador, int seleccion);

    boolean finalJuego();
}
