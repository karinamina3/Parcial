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
public interface Milicias {
    
    /**
     * Envia un entero con el dato del valor del ataque
     * @return valor de ataque
     */
    int atacar();
    
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
