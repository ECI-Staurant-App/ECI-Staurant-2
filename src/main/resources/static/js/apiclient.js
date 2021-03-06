apiclient = (function () {
   var zelda = "https://ecistaurant.herokuapp.com";
   //var zelda =  "http://localhost:8080";
    return {

        getAllRestaurants: function(callback) {
            //http://localhost:8080/
            $.getJSON(zelda+"/restaurants/",function (data) {
                callback(data);
            },null)
        },

        getAllUsers: function(callback) {
            $.getJSON(zelda+"/users/",function(data){
                callback(data);
            },null)

        },
        getLastOrderOfUser: function(user,callback) {
            $.getJSON(zelda+"/users/"+user+"/LastOrder",function(data){
                callback(data);
            },null)

        },
        getUserByCarne: function(usuario, callback) {

            $.getJSON(zelda+"/users/"+usuario,function(data){
                callback(data);
            },null)
        },
        getMenuByRestaurant : function (restaurante,callback) {

            $.getJSON(zelda+"/restaurants/"+restaurante+"/menus",function (data) {
                callback(data);
            }, null)
        },
        getAllTables : function (callback){

            $.getJSON(zelda+ "/users/Alltables",function (data) {
                callback(data);
            }, null )
        },
        placeOrder : function(usuario,restaurante,platillo) {

            console.log("USER :"+usuario);
            console.log("REST :"+restaurante);
            console.log("PLAT :"+platillo);
            var postPromise = $.ajax({
                url: zelda+"/users/AddOrder/",
                type: 'POST',
                data: 'platillo='+platillo+'&restaurante='+restaurante+'&user='+usuario,
                error: function (jqxhr,status,exception) {
                    alert('Exception:' ,exception);
                }
            });
            window.open(zelda+"/estadoPedido.html",'_blank');
            postPromise.then(
                function () {
                    console.info('OK');

                },
                function () {
                    console.info('NOK');
                }
            );
        },

        updateMesaState : function(mesa,estado){

            var putPromise = $.ajax({
                url: zelda+"/users/mesa/" + mesa,
                type: 'PUT',
                data: 'estado='+estado,
                error: function (jqxhr,status,exception) {
                    alert('Exception:' ,exception);
                }
            });
            putPromise.then(
                function () {
                    console.info('OK');
                },
                function () {
                    console.info('NOK');
                }
            );

        },


        getSaldo: function(correo,callback) {
            //http://localhost:8080/
            $.getJSON(zelda+"/users/saldo/" +correo,function (data) {
                callback(data);
            },null)
        },

        updateSaldo : function(carne,saldo){

            var putPromise = $.ajax({
                url: zelda+"/users/" + carne,
                type: 'PUT',
                data: 'saldo='+saldo,
                error: function (jqxhr,status,exception) {
                    alert('Exception:' ,exception);
                }
            });
            putPromise.then(
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