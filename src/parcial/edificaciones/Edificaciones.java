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
public interface Edificaciones {
    
    /**
     * Modifica la vida si es atacado
     * @param ataque 
     */
    void recibirAtaque(int ataque);
    
    /**
     * Retorna el valor de la vida
     * @return 
     */
    int getVida();
}
