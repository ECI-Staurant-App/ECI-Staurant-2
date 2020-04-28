var services = (function () {
    var api = apirestaurant;
    var user;


    function mapOrder(order){
        return order.map(function (rt) {
            return {
                orderId: rt.idPedido,
                orderFecha: rt.fecha,
                orderMenu: rt.menu,
                orderEstudiante: rt.usuario,
                orderEstado: rt.estado
            }
        })
    }

    function llenaHistorial(listaOrdenes){
        $("#pedidosTable").empty();
        console.log(listaOrdenes);
        var ordenes = mapOrder(listaOrdenes);

        for (i=0; i< ordenes.length ; i++){
            var estado = ordenes[i].orderEstado;
            var id = ordenes[i].orderId;
            if (estado == "pendiente") {
                var fila = "<tr data-status=\"" + estado + "\"><td><div> <button type=\"button\" class=\"btn btn-warning\" onclick='services.changeEstado("+ '&quot;'+estado+ '&quot;'+","+ id+")'>Finalizar</button></div></td><td><div class=\"media\"><a class=\"pull-left\"><img src=\"" + ordenes[i].orderMenu.url + "\" class=\"media-photo\"></a><div class=\"media-body\"><span class=\"media-meta pull-right\">" + ordenes[i].orderFecha + "</span><h4 class=\"title\">" + ordenes[i].orderMenu.nombre + "<span class=\"pull-right " + estado + "\">" + "(" + estado + ")" + "</span></h4><p class=\"summary\">" + ordenes[i].orderEstudiante.name + " </p></div></div></td></tr>"
            }
            else if (estado == "nuevo"){
                var fila = "<tr data-status=\"" + estado + "\"><td><div> <button type=\"button\" class=\"btn btn-danger\" onclick='services.changeEstado("+ '&quot;'+estado+ '&quot;'+","+ id+")'>Atender</button></div></td><td><div class=\"media\"><a  class=\"pull-left\"><img src=\"" + ordenes[i].orderMenu.url + "\" class=\"media-photo\"></a><div class=\"media-body\"><span class=\"media-meta pull-right\">" + ordenes[i].orderFecha + "</span><h4 class=\"title\">" + ordenes[i].orderMenu.nombre + "<span class=\"pull-right " + estado + "\">" + "(" + estado + ")" + "</span></h4><p class=\"summary\">" + ordenes[i].orderEstudiante.name + " </p></div></div></td></tr>"
            }
            else{
                var fila = "<tr data-status=\"" + estado + "\"><td><div> <button type=\"button\" class=\"btn btn-success disabled\">Completado!</button></div></td><td><div class=\"media\"><a class=\"pull-left\"><img src=\"" + ordenes[i].orderMenu.url + "\" class=\"media-photo\"></a><div class=\"media-body\"><span class=\"media-meta pull-right\">" + ordenes[i].orderFecha + "</span><h4 class=\"title\">" + ordenes[i].orderMenu.nombre + "<span class=\"pull-right " + estado + "\">" + "(" + estado + ")" + "</span></h4><p class=\"summary\">" + ordenes[i].orderEstudiante.name + " </p></div></div></td></tr>"
            }
            $("#pedidosTable").append(fila);
        }
        //conexion.limpiarNotificaciones(user);
    }

    function changeEstado(actual,id){
        var estados=["nuevo","pendiente","completado"];
        var ahora = estados.indexOf(actual);
        var nuevo = estados[(ahora+1)%estados.length];
        api.changeOrderState(user,id,nuevo);
        conexion.connectAndSendOrder(id);

    }

    function funcione(){
        user = sessionStorage.getItem("selectedUser");
        api.getOrdersByRestaurant(user,llenaHistorial);
    }
    return {
        funcione:funcione,
        changeEstado:changeEstado
    }

})();
