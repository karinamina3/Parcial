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

    /**
     * Se encarga de mostrar los que hay dentro de los 3 arrayList de construicciones
     */
    void mostrar();

    /**
     * Recibe el valor de los dos costos para verificar si al restar esos
     * costos al valor de recursos no daría un número negativo
     * @param costor1 recurso 1
     * @param costor2 recurso 2
     * @return true si la compra se puede realizar sin ningún problema
     */
    boolean poderConstruirEdificios(int costor1, int costor2);

    /**
     * Recibe el valor de los dos costos para verificar si al restar esos
     * costos al valor de recursos no daría un número negativo y tambien verifica 
     * que ya exista una edificación entrenar milicias
     * @param costor1 recurso 1
     * @param costor2 recurso 3
     * @return true si la compra se puede realizar sin ningún problema
     */
    boolean poderConstruirMilicias(int costor1, int costor2);

    /**
     * Recorre el arrayList donde se guardan las milicias y verifica
     * si no hay otro especialista ya creado
     * @return true si no existe una instancia de especialista
     */
    boolean poderConstruirEspecialista();
    
    /**
     * Recorre el hashMap donde se guardan las milicias en espera y
     * verifica que no haya otro especialista esperando
     * @return true si no existe una instancia de especialista
     */
    public boolean poderConstruirEspecialistaAux();

    /**
     * Recibe el valor de los dos costos para verificar si al restar esos
     * costos al valor de recursos no daría un número negativo y tambien verifica
     * que ya exista una edificación construir vehículos
     * @param costor1
     * @param costor2
     * @return 
     */
    boolean poderConstruirVehiculos(int costor1, int costor2);

    /**
     * Muestra el nombre de los recursos y la cantidad existente
     * @return 
     */
    String mostrarCentro();

    /**
     * Función que maneja la construcción de las tres diferentes unidades
     * recibe la fase del juego para calcular el tiempo de espera y guarda
     * los datos en tres HashMap diferentes
     * @param fase 
     */
    void construir(int fase);

    /**
     * Función que recorre los tres HashMap que se llenan en el método construir()
     * y verifica si la fase del juego ya coincide con la fase que la construcción espera
     * para enviarla a su debido arrayList
     * @param fase 
     */
    void fasesEspera(int fase);

    /**
     * Listado de opciones
     */
    void opcionesConstruir();

    /**
     * Método que ayuda a la validación de los datos que se le piden al jugador
     * @return la selección del jugador
     */
    int cuatroOpciones();

    /**
     * Método que ayuda a la validación de los datos que se le piden al jugador
     * @return la selección del jugador
     */
    int tresOpciones();

    /**
     * Método que ayuda a la validación de los datos que se le piden al jugador
     * @return la selección del jugador
     */
    int dosOpciones();

    /**
     * Operaciones que se deben de hacer para aumentar el nivel al centro de mando
     * @param mejora
     * @return true si la mejora se pudo realizar
     */
    boolean mejorarCentro(double mejora);

    /**
     * Se utiliza para aumentar en cada fase la cantidad estipulada en la edificación
     * ya sea que el jugador lo pida o no
     */
    void recolectarAux();

    /**
     * Método que llama el jugador para poder recolectar los recursos
     * @param flag asegura que solo una vez se recoga por fase
     */
    void recolectarRecurso(int flag);

    /**
     * Función que aumenta la cantidad al recurso 3 cada vez que este sea llamado
     * @param flag asegura que utilizarlo una vez en cada fase
     */
    void generarRecurso(int flag);

    /**
     * Pide las indicaciones al jugador de las opciones que tiene para atacar al oponente
     * @param r recibe la referencia de la raza del otro jugador 
     * @return true para verificar si pudo atacar en esta fase
     */
    boolean atacar(R r);

    /**
     * Método que asegura que la raza oponente reciba el ataque
     * @param atacador es enviado por la función atacar()
     * @param seleccion la construcción donde ira el ataque
     * @return 
     */
    boolean recibirAtaque(R atacador, int seleccion);

    /**
     * Verifica si el centro de mando tiene vida
     * @return true si la vida del centro es igual a cero o menor
     */
    boolean finalJuego();
}
