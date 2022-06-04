/*
 * Copyright (c) 2022. Operación Kami - Kaze.
 *
 * Licensed under the GNU General Public License v3.0
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Permissions of this strong copyleft license are conditioned on making available complete
 * source code of licensed works and modifications, which include larger works using a licensed
 * work, under the same license. Copyright and license notices must be preserved. Contributors
 * provide an express grant of patent rights.
 */

package es.kamikaze.app.model;

import java.util.Random;

public class User {

    //JAVA SINGLETONE CLASS para gestionar las variables del usuario

    private static User instancia;
    private int oro, at, def, vel, ps, exp;
    private String Username;

    public User() {
        instancia = this;
        oro = 0;
        at = 1;
        def = 1;
        vel = 1;
        ps = 15;
        exp = 0;

        //generar nombre de usuario random, hay poca posibilidad de que se repitan nombres pero deberíamos comprobar que no hay nadie con el mismo nombre antes de asignarlo
        int leftLimit = 97; // letter 'a'0
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        Username = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();
    }

    public User(String username, int oro, int at, int def, int vel, int ps, int exp, int lvl) {
        instancia = this;

        this.oro = oro;
        this.at = at;
        this.def = def;
        this.vel = vel;
        this.ps = ps;
        this.exp = exp;
    }

    public static User getInstancia() {
        return instancia;
    }

    /*      GETTERS AND SETTERS        */

    public static void setInstancia(User instancia) {
        User.instancia = instancia;
    }

    public int getLevel() { //los niveles subirán de 20 puntos en 20 puntos
        int result = 0;
        if (exp != 0) {
            result = (exp - (exp % 20)) / 20;
        }
        return result;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}