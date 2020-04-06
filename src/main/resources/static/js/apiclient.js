apiclient = (function () {

    return {

        getAllRestaurants: function(callback) {
            //http://localhost:8080/
            $.getJSON("http://localhost:8080/restaurants/",function (data) {
                callback(data);
            },null)
        },

        getAllUsers: function(callback) {
            $.getJSON("http://localhost:8080/users/",function(data){
                callback(data);
            },null)

        },
        getUserByCarne: function(usuario, callback) {

            $.getJSON("http://localhost:8080/users"+usuario,function(data){
                callback(data);
            },null)
        },
        getMenuByRestaurant : function (restaurante,callback) {

            $.getJSON("http://localhost:8080/restaurants/"+restaurante+"/menus",function (data) {
                callback(data);
            }, null)
        },
        placeOrder : function(usuario,restaurante,platillo) {

            console.log("USER :"+usuario);
            console.log("REST :"+restaurante);
            console.log("PLAT :"+platillo);
            var postPromise = $.ajax({
                url: "http://localhost:8080/AddOrder/"+usuario+restaurante+platillo,
                type: 'POST',
            });

            postPromise.then(
                function () {
                    console.info('OK');
                },
                function () {
                    console.info('NOK');
                }
            );
        }

    }

})();