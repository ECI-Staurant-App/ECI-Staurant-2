var conexion = (function () {
    var stompClient=null;


    function initStompClient(){
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
    };

    function sendNotification(restaurante){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.send("/app/" + restaurante + '/newOrders', {},true);

        });
    };

    function connectAndSubscribeOrder(id,restaurante){
        initStompClient();
        stompClient.connect({}, function (frame) {
            var rest=restaurante;
            stompClient.send("/app/" + restaurante + '/newOrders', {},true);
            stompClient.subscribe("/topic/Pedido/" + id,function(eventbody){
                console.log("Luego lo pienso mejor, aqui solo cambio de estado en vista");
                document.getElementById(eventbody.body).className="active step0";
                if (eventbody.body == "completado"){
                    alertify.confirm("Su pedido está listo :) Recójalo en: " + rest,null,null)
                }
            });
        });
    };

    function connectAndSendOrder(id){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.send("/app/Pedido/" + id, {},id);
            location.reload();
        });
    };

    function connectAndSendMesa(id){
        initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.send("/app/Mesa/" + id, {},id);
        });
    };

    function connectAndSubscribeMesa(){initStompClient();
        stompClient.connect({}, function (frame) {
            stompClient.subscribe("/topic/Mesas",function(eventbody){

                var helper = JSON.parse(eventbody.body);
                console.log(helper.id);
                console.log(helper.time);
                if (helper.time!= 0 || helper.time != ""){
                    document.getElementById("botonmesa" + helper.id).style.display = 'none';
                    document.getElementById("tiempomesa" + helper.id).style.display = 'inline';
                    document.getElementById("tiempomesa" + helper.id).textContent=helper.time;
                    document.getElementById("disponiblemesa" + helper.id).textContent="Disponible: No";
                }
                else{
                    apiclient.updateMesaState(helper.id,true);
                    document.getElementById("tiempomesa" + helper.id).style.display = 'none';
                    document.getElementById("disponiblemesa" + helper.id).textContent="Disponible: Si";
                    document.getElementById("botonmesa" + helper.id).style.display = 'inline';
                }


            });
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
        limpiarNotificaciones:limpiarNotificaciones,
        connectAndSubscribeOrder:connectAndSubscribeOrder,
        connectAndSendOrder:connectAndSendOrder,
        connectAndSendMesa:connectAndSendMesa,
        connectAndSubscribeMesa:connectAndSubscribeMesa

    };


})();