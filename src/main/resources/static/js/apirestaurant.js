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
        }
    }

})();