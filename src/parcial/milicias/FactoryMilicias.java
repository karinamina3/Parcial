/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.milicias;

import parcial.edificaciones.Edificaciones;
import parcial.vehiculos.Vehiculos;
import parcial.AbstractFactory;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class FactoryMilicias implements AbstractFactory{

    @Override
    public Edificaciones getEdificaciones(int tipo, int vida, int recurso) {
        return null;
    }

    @Override
    public Vehiculos getVehiculos(int tipo, int vida, int ataque) {
        return null;
    }

    @Override
    public Milicias getMilicias(int tipo, int vida, int ataque) {
            switch (tipo){
            case 1:
                return new Escuadrones(vida, ataque);
            case 2:
                return new Especialistas(vida, ataque);            
        }
        return null;
    }
    
}