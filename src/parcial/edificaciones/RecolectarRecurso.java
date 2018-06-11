/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.edificaciones;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class RecolectarRecurso implements Edificaciones {

    private int vida, fases;

    public RecolectarRecurso(int vida, int fases) {
        this.vida = vida;
        this.fases = fases;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFases() {
        return fases;
    }

    public void setFases(int fases) {
        this.fases = fases;
    }

}
