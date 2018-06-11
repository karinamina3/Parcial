/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import parcial.edificaciones.Edificaciones;
import parcial.milicias.Milicias;
import parcial.vehiculos.Vehiculos;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public interface AbstractFactory {

    Edificaciones getEdificaciones(int tipo, int vida, int fases);

    Vehiculos getVehiculos(int tipo, int vida, int fases, int ataque);

    Milicias getMilicias(int tipo, int vida, int fases, int ataque);
}
