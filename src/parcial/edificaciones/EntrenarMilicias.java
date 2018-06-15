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
public class EntrenarMilicias implements Edificaciones {

    private int vida;

    public EntrenarMilicias(int vida) {
        this.vida = vida;        
    }

    @Override
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    } 
    
    @Override
    public String toString(){
        return "Edificación de Entrenar Milicias";
    }
    
    @Override
    public void recibirAtaque(int ataque) {
        if (vida > 0) {
            setVida(getVida() - ataque);
        }
    }

}
