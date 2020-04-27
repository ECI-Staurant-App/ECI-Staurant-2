var conexion = (function () {
    var stompClient=null;


    function initStompClient(){
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
    };

    function sendNotification(restaurante){
        initStompClient();
        console.log("Gonorreeeeeeeeeeeeeeeeaaaaaaaaaaaa");
        stompClient.connect({}, function (frame) {
            stompClient.send("/app/" + restaurante + '/newOrders', {},true);

        });
    };

    function connectAndSubscribeOrder(id){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.subscribe("/topic/Pedidos/" + id, {},function(eventbody){
                console.log("Luego lo pienso mejor, aqui solo cambio de estado en vista");
            });
        });
    };

    function connectAndSendOrder(id){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.send("/topic/Pedido/" + id, {},id);
        });
    };

    function connectAndSubscribeNotifications(){
        initStompClient();
        var restaurante = services.getSelectedUser();
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/'+restaurante+'/newOrders', function (eventbody) {
                console.log(eventbody.body);
                document.getElementById('noti').textContent=eventbody.body;
            });
            stompClient.send("/app/" + restaurante + '/newOrders', {},false);


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

    function limpiarNotificaciones(restaurante){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.send("/app/" + restaurante + '/cleanNotifications', {});
        });

    };




    return{
        init:initStompClient,
        connectAndSubscribeNotifications:connectAndSubscribeNotifications,
        sendNotification:sendNotification,
        limpiarNotificaciones:limpiarNotificaciones

    };


})();