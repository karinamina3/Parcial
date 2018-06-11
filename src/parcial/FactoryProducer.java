/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import parcial.edificaciones.FactoryEdificaciones;
import parcial.milicias.FactoryMilicias;
import parcial.vehiculos.FactoryVehiculos;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(int tipo){
        switch (tipo){
            case 1:
                return new FactoryEdificaciones();
            case 2:
                return new FactoryMilicias();
            case 3:
                return new FactoryVehiculos();
        }
        return null;
    }
}