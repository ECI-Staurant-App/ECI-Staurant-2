package edu.eci.arsw.ecistaurant.model;

import edu.eci.arsw.ecistaurant.controllers.ConnectionsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Timer;
import java.util.TimerTask;

/**
 *Simple countdown timer of java.util.Timer facility.
 */
public class Countdown {

    @Autowired
    SimpMessagingTemplate mgt;

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
                //System.out.println();
                //ConnectionsController.actualiceTiempoMesas(getIdMesa(),i);
                //mgt.convertAndSend("/topic/Mesas", "Mesa = "+idMesa + " t= " + i);
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

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
}