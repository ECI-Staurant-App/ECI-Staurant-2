package edu.eci.arsw.ecistaurant.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectionsController {

    @Autowired
    SimpMessagingTemplate mgt;

    @MessageMapping("/{restaurant}/newOrders")
    //Point p, @DestinationVariable String
    public void handleNotificationsNewOrders(@DestinationVariable String restaurant,String s) throws Exception {
        mgt.convertAndSend("/topic/"+restaurant+"/newOrders", "Tienes una nueva orden id del pedido:!" + s);
        //Aqui mando el topico y con esto mando el objeto, lo modifico y le hago cualquier cosa
        // y lo envio a todo el mundo que
        //este suscrito a el
        /*
        El mandar el topico implica que el objeto o cualquier cosa que se haga aqui
        se va a mandar a todos los que esten suscritos al topico
        es decir puedo enviar un string
         */
    }

    @MessageMapping("/newOrder.{id}")
    public void handleNewOrders(@DestinationVariable int id) throws Exception{


    }



}
