tableServices = (function () {

    var api = apiclient;
    var mesas;
    var mesaSeleccionada;

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


    function fillCards(mesa) {

        mesas = doMap(mesa);
        console.log(mesas);

        for (i in mesas) {
            var fila = "";
            var slide = "";
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
            var card = '<div class="col-xs-12 col-sm-6 col-md-4">' +
                '<div class="image-flip" ontouchstart="this.classList.toggle(\'hover\');"> ' +
                '<div class="mainflip">' +
                '<div class="frontside">' +
                '<div class="card">' +
                '<div class="card-body text-center">' +
                '<p> <img class="img-thumbnail" src=/img/table.png alt="card image"></p>' +
                '<h4 class="card-title">' + 'Mesa ' + id + '</h4>' +
                '<p class="card-text"></p>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="backside">' +
                '<div class="card">' +
                '<div class="card-body text-center mt-4">' +
                '<h4 class="card-title">' + 'Mesa ' + id + ' info' + '</h4>' +
                '<p class="card-text">' + 'Ubicacion: ' + ubicacion + '</p>' +
                '<p class="card-text">' + ' Disponible: ' + disponibilidad + '</p>' +
                '<p class="card-text">' + ' Tiempo Restante: ' + timeLeft + '</p>' +
                boton +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';

            $("#fillCards").append(card);
        }

    }

    function notificar() {
        alert("Esta mesa ya está reservada")
    }

    function panelPedido() {

        var confirm = alertify.confirm("Confirmación de su pedido", "Usuario: " + services.getUser() + " " + "\n" +
            "Restaurante: " + services.getRestaurant() + " " + "\n" +
            "Menu: " + services.getMenu() + " " + "\n" +
            "Mesa: " + sessionStorage.getItem("mesaSeleccionada"), null, null).set('labels', {
            ok: 'Confirmar',
            cancel: 'Cancelar'
        });

        confirm.set('onok', function () {

            services.placeOrder();
            alertify.success("Su pedido ha sido registrado");
        });
        confirm.set('oncancel', function () {
            alertify.error("Su pedido ha sido cancelado");
        });
    }

    function funcione() {
        api.getAllTables(fillCards);

    }

    return {
        funcione: funcione,
        notificar: notificar,
        panelPedido: panelPedido,
        setMesaSeleccionada: setMesaSeleccionada,
    }

})();
