package edu.eci.arsw.ecistaurant.model;

import edu.eci.arsw.ecistaurant.controllers.ConnectionsController;

import java.util.Timer;
import java.util.TimerTask;

/**
 *Simple countdown timer of java.util.Timer facility.
 */
public class Countdown {
    private int duracion;
    private int idMesa;

    public Countdown (int duracion, int idMesa){
        this.duracion = duracion;
        this.idMesa = idMesa;
    }

    public void inicie() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = duracion;
            public void run() {
                System.out.println(i--);
                ConnectionsController.actualiceTiempoMesas(idMesa,i);
                if (i< 0)
                    timer.cancel();
            }
        }, 0, 1000);
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}