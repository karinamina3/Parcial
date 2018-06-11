/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.vehiculos;

import parcial.edificaciones.Edificaciones;
import parcial.milicias.Milicias;
import parcial.AbstractFactory;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class FactoryVehiculos implements AbstractFactory {

    @Override
    public Edificaciones getEdificaciones(int tipo, int vida, int fases) {
        return null;
    }

    @Override
    public Vehiculos getVehiculos(int tipo, int vida, int fases, int ataque) {
        switch (tipo){
            case 1:
                return new Tipo1(vida, fases, ataque);
            case 2:
                return new Tipo2(vida, fases, ataque);            
        }
        return null;
    }

    @Override
    public Milicias getMilicias(int tipo, int vida, int fases, int ataque) {
        return null;
    }
    
}
