package edu.eci.arsw.ecistaurant.controllers;


import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class ConnectionsController {

    @Autowired
    SimpMessagingTemplate mgt;

    @Autowired
    ServiciosRestaurante serviciosRestaurante;

    @Autowired
    ServiciosEstudiante serviciosEstudiante;

    @Autowired
    private static SimpMessagingTemplate mgt2;

    private int newNotifications = 0;


    @MessageMapping("/{restaurant}/newOrders")
    //Point p, @DestinationVariable String
    public void handleNotificationsNewOrders(@DestinationVariable String restaurant, boolean cliente) throws Exception {
        if (cliente){
            newNotifications+=1;
        }

        mgt.convertAndSend("/topic/"+restaurant+"/newOrders", "Tienes: " + newNotifications +" Nuevos pedidos!");
        //Aqui mando el topico y con esto mando el objeto, lo modifico y le hago cualquier cosa
        // y lo envio a todo el mundo que
        //este suscrito a el
        /*
        El mandar el topico implica que el objeto o cualquier cosa que se haga aqui
        se va a mandar a todos los que esten suscritos al topico
        es decir puedo enviar un string
         */
    }


    @MessageMapping("/cleanNotifications")
    public void cleanNotifications() throws Exception{
        newNotifications=0;
    }

    @MessageMapping("/Pedido/{idPedido}")
    public void handleOrders(@DestinationVariable int idPedido) throws Exception{
        Pedido actual = serviciosRestaurante.getPedidoById(idPedido);
        String estado= actual.getEstado();
        mgt.convertAndSend("/topic/Pedido/"+idPedido, estado);
    }

    @MessageMapping("/Mesa/{idMesa}")
    public void handleMesas(@DestinationVariable int idMesa) throws EcistaurantPersistenceException {
        //serviciosEstudiante.seleccionarMesa(idMesa);
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 420;
            public void run() {
                System.out.println(i--);
                //System.out.println();
                //connectionsController.actualiceTiempoMesas(getIdMesa(),i);
                mgt.convertAndSend("/topic/Mesas", "Mesa = "+idMesa + " t= " + i);
                if (i< 0)
                    timer.cancel();
            }
        }, 0, 1000);

    }

    public void actualiceTiempoMesas(int idMesa,int tiempo){

        mgt.convertAndSend("/topic/Mesas", "Mesa = "+idMesa + " t= " + tiempo);
    }






}
