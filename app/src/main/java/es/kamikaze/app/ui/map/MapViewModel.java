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

package es.kamikaze.app.ui.map;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MapViewModel extends ViewModel {

    private ArrayList<Marker> enemigos = new ArrayList<>();
    private Boolean enemiesStarted = false;
    private Thread generaEnemigos;

    public void startEnemies(OnEnemySpawnListener listener) {
        if (!enemiesStarted) {
            enemiesStarted = true;
            Random rand = new Random(); //instance of random class

            generaEnemigos = new Thread(() -> {
                //cada intervalo aleatorio de 1 a 3 minutos crea un enemigo aleatorio
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        if (enemiesStarted) {
                            if (enemigos.size() > 3) {
                                listener.enemyDelete(enemigos.get(0));
                                deleteEnemigo();
                            } else {
                                listener.enemySpawn();
                            }
                        }
                    }
                }, 0, (60 * 100) * (rand.nextInt(3) + 1));

            });

            generaEnemigos.start();
        }
    }

    public void addEnemigo(Marker enemigo) {
        enemigos.add(enemigo);
        Log.d("XYZ", "Enemigos: " + enemigos);
    }

    public void deleteEnemigo() {
        if (enemigos.size() > 0) {

            //enemigos.get(0).remove();

            enemigos.remove(0);
        }

        Log.d("XYZ DELETE", "Enemigos: " + enemigos);
    }

    public void pause() {
        enemiesStarted = false;
    }
}