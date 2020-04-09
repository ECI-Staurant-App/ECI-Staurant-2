var services = (function () {
    var api = apirestaurant;


    function mapOrder(order){
        return order.map(function (rt) {
            return {
                orderId: rt.idPedido,
                orderFecha: rt.fecha,
                orderMenu: rt.menu,
                orderEstudiante: rt.usuario
            }
        })
    }

    function llenaHistorial(listaOrdenes){
        console.log(listaOrdenes);
        var ordenes = mapOrder(listaOrdenes);

        for (i=0; i< ordenes.length ; i++){
            var estado = "pagado";
            var fila = "<tr data-status=\"" + estado + "\"><td><div class=\"ckbox\"><input type=\"checkbox\" id=\"checkbox1\"><label for=\"checkbox1\"></label></div></td><td><div class=\"media\"><a href=\"#\" class=\"pull-left\"><img src=\""+ordenes[i].orderMenu.url+"\" class=\"media-photo\"></a><div class=\"media-body\"><span class=\"media-meta pull-right\">"+ordenes[i].orderFecha+"</span><h4 class=\"title\">"+ordenes[i].orderMenu.nombre+"<span class=\"pull-right " +estado+"\">"+ "(" +estado + ")"+"</span></h4><p class=\"summary\">"+ ordenes[i].orderEstudiante.name+" </p></div></div></td></tr>"
            $("#pedidosTable").append(fila);
        }
    }

    function funcione(){
        var u = sessionStorage.getItem("selectedUser");
        api.getOrdersByRestaurant(u,llenaHistorial);
    }
    return {
        funcione:funcione,
    }

})();
