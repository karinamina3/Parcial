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
public class Tipo2 implements Vehiculos {

    private int vida, ataque;

    public Tipo2(int vida, int ataque) {
        this.vida = vida + 50;
        this.ataque = ataque;
    }

    @Override
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    @Override
    public String toString() {
        return "Vehículo 2";
    }

    @Override
    public int atacar() {
        return this.ataque;
    }
    
    @Override
    public void recibirAtaque(int ataque) {
        if (vida > 0) {
            setVida(getVida() - ataque);
        }
    }
}
