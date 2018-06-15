/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.milicias;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class Escuadrones implements Milicias {
    private int vida, ataque;        

    public Escuadrones(int vida, int ataque) {
        this.vida = vida;        
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
    public String toString(){
        return "Escuadrón";
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
