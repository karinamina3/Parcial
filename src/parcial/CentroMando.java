/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

/**
 *
 * @author Karina Mina <https://github.com/karinamina3>
 */
public class CentroMando {

    private int vida;
    private int recurso1, recurso2, recurso3;
    private int tr1, tr2, tr3;

    public CentroMando(int vida, int recurso1, int recurso2, int recurso3, int tr1, int tr2, int tr3) {
        this.vida = vida;
        this.recurso1 = recurso1;
        this.recurso2 = recurso2;
        this.recurso3 = recurso3;
        this.tr1 = tr1;
        this.tr2 = tr2;
        this.tr3 = tr3;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getRecurso1() {
        return recurso1;
    }

    public void setRecurso1(int recurso1) {
        this.recurso1 = recurso1;
    }

    public int getRecurso2() {
        return recurso2;
    }

    public void setRecurso2(int recurso2) {
        this.recurso2 = recurso2;
    }

    public int getRecurso3() {
        return recurso3;
    }

    public void setRecurso3(int recurso3) {
        this.recurso3 = recurso3;
    }

    public int getTr1() {
        return tr1;
    }

    public void setTr1(int tr1) {
        this.tr1 = tr1;
    }

    public int getTr2() {
        return tr2;
    }

    public void setTr2(int tr2) {
        this.tr2 = tr2;
    }

    public int getTr3() {
        return tr3;
    }

    public void setTr3(int tr3) {
        this.tr3 = tr3;
    }

}
