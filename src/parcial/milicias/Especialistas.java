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
public class Especialistas implements Milicias{
    
    private int vida, ataque;
    
    public Especialistas(int vida, int ataque) {       
        this.vida = vida;
        this.ataque = ataque;
    }

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
    public int atacar() {
//        if (fases == 0){
            System.out.println("Piuuu piu");
            return ataque;
//        }
//        return 0;
    }

    @Override
    public boolean vida() {
        if(vida > 0) {
            return true;
        }
        return false;
    }
        
}
