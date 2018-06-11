/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.razas;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class FactoryRazas {

    public static Razas getRazas(int tipo) {
        switch (tipo) {
            case 1:
                return new Piratas();
            case 2:
                return new Sirenas();
            case 3:
                return new Hadas();
        }
        return null;
    }
}
