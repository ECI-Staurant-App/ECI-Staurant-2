tableServices = (function () {

    var api = apiclient;
    var mesas;
    var mesaSeleccionada;
    //var zelda = "https://ecistaurant.herokuapp.com";
    var zelda = "http://localhost:8080";
    var clickCount = 0;

    function doMap(mesa) {
        return mesa.map(function (rt) {
            return {
                rtId: rt.idMesa,
                rtUbicacion: rt.ubicacion,
                rtIsAvailable: rt.estaDisponible,
                rtTimeLeft: rt.timeLeft
            }
        })
    }

    function getMesaSeleccionada() {
        return sessionStorage.getItem("mesaSeleccionada");
    }

    function setMesaSeleccionada(id) {
        console.log("IDMESA: " + id);
        mesaSeleccionada = id;
        sessionStorage.setItem("mesaSeleccionada", mesaSeleccionada);
        console.log(mesaSeleccionada);
        panelPedido();
    }

    function setMesaNull(){
        sessionStorage.removeItem('mesaSeleccionada');
    }

    function disableMeAfterOnceClick() {
        //
        // check form input values
        //
        console.log("Entre A DESACTIVARRRRR");
        var confirmar = document.getElementById("confirm");
        console.log("CONFIRMAR " + confirmar);
        var rechazar = document.getElementById("cancel");
        console.log("RECHAZAR " + rechazar);
    }

    function fillCards(mesa) {

        mesas = doMap(mesa);
        console.log(mesas);

        for (i in mesas) {

            var id = mesas[i].rtId;
            var ubicacion = mesas[i].rtUbicacion;
            var isAvailable = mesas[i].rtIsAvailable;
            var disponibilidad = "";
            var boton = "";
            if (isAvailable) {
                disponibilidad = "si";
                boton = '<a th:href="" class="btn btn-primary btn-sm" onclick="tableServices.setMesaSeleccionada(' + '&quot;' + id + '&quot;' + ')"><i class="fa fa-plus"></i></a>';
            } else {
                disponibilidad = "no";
                boton = '<a th:href="" class="btn btn-primary btn-sm" onclick="tableServices.notificar()"><i class="fa fa-plus"></i></a>'
            }
            var timeLeft = mesas[i].rtTimeLeft;
            console.log(ubicacion);
            console.log(isAvailable);
            var it = i;
            var card = '<div class="col" ontouchstart="this.classList.toggle(\'hover\');">' +
                '<div class="container">' +
                '<div class="front" style= "background-image: url(https://f0.pngfuel.com/png/807/535/table-knife-and-fork-logo-png-clip-art.png)">' +
                '<div class="inner">' +
                '<p>  MESA </p>' +
                '<span>'+ id + '</span>' +
                '</div>' +
                '</div>' +
                '<div class="back">' +
                '<div class="inner">' +
                '<p>' + 'Ubicación: ' + ubicacion +'</p>' +
                '<p>' + 'Disponible: ' + disponibilidad +'</p>' +
                     boton +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $("#infoMesas").append(card);
        }

    }
    function notificar() {
        alert("Esta mesa ya está reservada")
    }

    function panelPedido() {
        window.location.href = zelda + "/confirmOrder.html";
    }

    function confirmOrder(){
        var confirm = alertify.confirm("¿Desea cancelar su pedido?",null,null).set('labels', {
            ok: 'Si',
            cancel: 'No'
        });
       confirm.set('onok', function () {

           alertify.error("Su pedido ha sido cancelado exitosamente");
           window.location.href = zelda + "/UsersDashboard.html";
       });
       confirm.set('oncancel', function () {
           alertify.error("Su pedido no ha sido cancelado exitosamente");
       });
    }

    function llenarInfoPedido() {

        var usuario = services.getUser();
        var restaurante = services.getRestaurant();
        var menu = services.getMenu();
        var precio = services.getPrecioMenu();
        var mesaSelected = getMesaSeleccionada();
        console.log("MESA "+ mesaSelected);
        if (mesaSelected == undefined) {
            mesaSelected = "No seleccionó"
        }

        var tabla =' <table class="table-fill" id="infoPedido">' +
            '   <thead>' +
            '            <tr>' +
            '                <th class="text-left">Información de su pedido</th>' +
            '                <th class="text-left"></th>' +
            '            </tr>' +
            '            </thead>' +
                    '<tbody class="table-hover">' +
                        '<tr>' +
                '           <td class="text-left">Usuario</td>' +
                '           <td class="text-left">' + usuario + '</td>' +
                '       </tr>' +
                '            <tr>' +
                '                <td class="text-left">Restaurante</td>' +
                '                <td class="text-left">' + restaurante + '</td>' +
                '            </tr>' +
                '            <tr>' +
                '                <td class="text-left">Menu</td>' +
                '                <td class="text-left">' + menu + '</td>' +
                '            </tr>' +
                '            <tr>' +
                '                <td class="text-left">Precio</td>' +
                '                <td class="text-left">' + '$' + precio + '</td>' +
                '            </tr>' +
                '            <tr>' +
                '                <td class="text-left">Mesa</td>' +
                '                <td class="text-left">' + mesaSelected + '</td>' +
                '            </tr>' +
        '               </tbody>'+
            '        </table>' +
            '<nav class="codrops-demos">' +
            '    <a id="confirm" href="/UsersDashboard.html" onclick="services.placeOrder();" style="text-align:center"> Confirmar </a>' +
            '    <a id="cancel" href="#" onclick="tableServices.confirmOrder();" style="text-align:center"> Cancelar </a>' +
            '</nav>';

        $("#infoPedido").html("");
        $("#infoPedido").append(tabla);

    }

    
    function funcione() {
        api.getAllTables(fillCards);

    }

    return {
        funcione: funcione,
        notificar: notificar,
        panelPedido: panelPedido,
        setMesaSeleccionada: setMesaSeleccionada,
        llenarInfoPedido: llenarInfoPedido,
        confirmOrder :confirmOrder,
        setMesaNull : setMesaNull,
        disableMeAfterOnceClick : disableMeAfterOnceClick
    }
})();
