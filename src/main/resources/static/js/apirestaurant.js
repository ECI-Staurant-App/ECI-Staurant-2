apirestaurant = (function () {

    return {

        getAllOrders: function(callback) {
            $.getJSON("https://ecistaurant.herokuapp.com/restaurants/orders",function(data){
                callback(data);
            },null)

        },

        getOrdersByRestaurant: function(restaurant,callback) {
            $.getJSON("http://localhost:8080/restaurants/" + restaurant + "/orders",function(data){
                callback(data);
            },null)

        },
        getOrderByUser: function(usuario, callback) {

            $.getJSON("https://ecistaurant.herokuapp.com/restaurants"+usuario,function(data){
                callback(data);
            },null)
        },
        getLastOrders : function (callback) {

            $.getJSON("https://ecistaurant.herokuapp.com/restaurants/LastOrders",function (data) {
                callback(data);
            }, null)
        },

        changeOrderState : function (restaurante,idOrden,estado){
            var putPromise = $.ajax({
                url: "http://localhost:8080/restaurants/"+restaurante+"/orders/"+idOrden,
                type: 'PUT',
                data: estado,
                contentType:'text/plain',

                error: function (jqxhr,status,exception) {
                    alert('Exception:' ,exception);
                }
            });

            putPromise.then(
                function () {
                    console.info("OK");
                },
                function () {
                    console.info("ERROR");
                }
            );

        }


    }

})();