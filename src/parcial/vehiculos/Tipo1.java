/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.vehiculos;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Tipo1 implements Vehiculos{
    
    private int vida, fases, ataque;

    public Tipo1(int vida, int fases, int ataque) {
        this.vida = vida;
        this.fases = fases;
        this.ataque = ataque;
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

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    @Override
    public int atacar() {
        System.out.println("Piuuu piu");
        return ataque;
    }
    
    @Override 
    public boolean vida(){
        if(vida > 0) {
            return true;
        }
        return false;
    }
    
}
