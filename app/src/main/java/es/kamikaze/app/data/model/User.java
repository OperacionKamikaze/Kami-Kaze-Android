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

package es.kamikaze.app.data.model;

import java.util.Random;

import es.kamikaze.app.ui.activities.MainActivity;

public class User {

    //JAVA SINGLETONE CLASS para gestionar las variables del usuario

    private static User instancia;
    private int oro, at, def, vel, ps, exp, lvl;
    private String id, img, username;
    private Boolean ultimaBatalla, juegoIniciado;


    private User() {
        instancia = this;
        oro = 0;
        at = 1;
        def = 1;
        vel = 1;
        ps = 15;
        exp = 0;
        lvl = 1;
        ultimaBatalla = false;
        juegoIniciado = false;
        //generar nombre de usuario random, hay poca posibilidad de que se repitan nombres pero deberíamos comprobar que no hay nadie con el mismo nombre antes de asignarlo
        int leftLimit = 97; // letter 'a'0
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        id = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();

        username = id;
    }

    private User(String id, int oro, int at, int def, int vel, int ps, int exp, int lvl, String username) {
        instancia = this;

        this.oro = oro;
        this.at = at;
        this.def = def;
        this.vel = vel;
        this.ps = ps;
        this.exp = exp;
        this.lvl = lvl;
        this.username = username;
        ultimaBatalla = false;
        juegoIniciado = false;
    }


    public static synchronized User getInstancia() {
        if (instancia == null) {
            instancia = new User();
        }
        return instancia;
    }

    public static void setInstancia(User instancia) {
        User.instancia = instancia;
    }

    public void putExperience(int experience){
        while(((experience - (experience % 20)) / 20) != 0) {
            experience -= 20;
            levelUp();
        }
        this.exp += experience;
    }

    public void levelUp(){
        this.lvl += 1;
        this.ps = lvl * 15;
        this.oro += 25 * this.lvl;
        MainActivity.popupLevel();
    }



    /*      GETTERS AND SETTERS        */



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

    private void setExp(int exp) {
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getUltimaBatalla() {
        return ultimaBatalla;
    }

    public void setUltimaBatalla(Boolean ultimaBatalla) {
        this.ultimaBatalla = ultimaBatalla;
    }

    public Boolean getJuegoIniciado() {
        return juegoIniciado;
    }

    public void setJuegoIniciado(Boolean juegoIniciado) {
        this.juegoIniciado = juegoIniciado;
    }
}