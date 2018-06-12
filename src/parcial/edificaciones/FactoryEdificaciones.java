/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.edificaciones;

import parcial.milicias.Milicias;
import parcial.vehiculos.Vehiculos;
import parcial.AbstractFactory;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class FactoryEdificaciones implements AbstractFactory{
   
    @Override
    public  Edificaciones getEdificaciones(int tipo, int vida, int fases, int recurso) {
        switch (tipo){
            case 1:
                return new RecolectarRecurso(vida, fases, recurso);
            case 2:
                return new GenerarRecurso(vida, fases);
            case 3:
                return new EntrenarMilicias(vida, fases);
            case 4:
                return new ConstruirVehiculos(vida, fases);
        }
        return null;
    }

    @Override
    public Vehiculos getVehiculos(int tipo, int vida, int fases, int ataque) {
        return null;
    }

    @Override
    public Milicias getMilicias(int tipo, int vida, int fases, int ataque) {
        return null;
    }
}
