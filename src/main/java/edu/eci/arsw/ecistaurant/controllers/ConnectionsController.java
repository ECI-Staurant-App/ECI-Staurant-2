package edu.eci.arsw.ecistaurant.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectionsController {

    @Autowired
    SimpMessagingTemplate mgt;

    @MessageMapping("/newpoint.{id}")
    //Point p, @DestinationVariable String
    public void handlePointEvent() throws Exception {
        //mgt.convertAndSend("/topic/newpoint."+id, p);
        //Aqui mando el topico y con esto mando el objeto, lo modifico y le hago cualquier cosa
        // y lo envio a todo el mundo que
        //este suscrito a el
        /*
        El mandar el topico implica que el objeto o cualquier cosa que se haga aqui
        se va a mandar a todos los que esten suscritos al topico
         */
    }

}
