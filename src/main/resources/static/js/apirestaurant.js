apirestaurant = (function () {
    var zelda = "https://ecistaurant.herokuapp.com";
    //var zelda = "http://localhost:8080";
    return {


        getAllOrders: function(callback) {
            $.getJSON(zelda+"/restaurants/orders",function(data){
                callback(data);
            },null)

        },

        getOrdersByRestaurant: function(restaurant,callback) {
            $.getJSON(zelda+"/restaurants/" + restaurant + "/orders",function(data){
                callback(data);
            },null)

        },
        getOrderByUser: function(usuario, callback) {

            $.getJSON(zelda+"/restaurants"+usuario,function(data){
                callback(data);
            },null)
        },
        getLastOrders : function (callback) {

            $.getJSON(zelda+"/restaurants/LastOrders",function (data) {
                callback(data);
            }, null)
        },

        changeOrderState : function (restaurante,idOrden,estado){
            var putPromise = $.ajax({
                url: zelda+"/restaurants/"+restaurante+"/orders/"+idOrden,
                type: 'PUT',
                data: estado,
                contentType:'text/plain',

                error: function (jqxhr,status,exception) {
                    alert('Exception:' ,exception);
                }
            });

            putPromise.then(
                function () {
                    conexion.connectAndSendOrder(idOrden);
                    console.info("OK");
                },
                function () {
                    console.info("ERROR");
                }
            );

        }


    }

})();