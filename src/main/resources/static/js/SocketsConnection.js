var con = (function () {

    var connectAndSubscribe = function(){
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/', function (eventbody) {
                //Aqui debo hacer lo que reciba en el path de destinatio

            });

            //Debo hacer los demas subscribe que necesite.
            // La pregunta es ¿Quien abrira el socket y quien se conectara?
            //Al parecer abro lo que haya que hacer con send .. con ello abro el topico y dejo
            //que los demas se suscriban a este topico
            /*
            osea los clientes se sucriben al topico /topic/nuevosPedidos para que cuando
            haga un pedido le aparezca la notificacion.
            ¿Como? cuando se haga un pedido voy a hacer un .send al /app/metodo messagemapping
             con un string que me retorne un topico /topic/nuevosPedidos
            con esto, el mismo restaurante que esta suscrito a este topico reciba la notificacion
             y lo reirija a la pagina de pedidos o simplemente
            un alert yo que se
            Y ADEMAS se suscriben al topico /topic/nuevoPedido.IdPedido
             */
        });


    };



    return{
        connect: function () {

        },

        disconnect: function () {

        }
    };


});