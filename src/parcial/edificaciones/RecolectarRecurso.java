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

    private int vida, tipo, recurso = 100;

    public RecolectarRecurso(int vida, int tipo) {        
        this.vida = vida;        
        this.tipo = tipo;
    }

    @Override
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }            

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

     @Override
    public String toString(){
        return "Edificación de Recolectar Recuso";
    }
    
    @Override
    public void recibirAtaque(int ataque) {
        if (vida > 0) {
            setVida(getVida() - ataque);
        }
    }
}
