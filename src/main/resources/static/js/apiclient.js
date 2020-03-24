apiclient = (function () {

    return {

        getAllRestaurants: function(callback) {
            $.getJSON("https://ecistaurant.herokuapp.com/restaurants/",function (data) {
                callback(data);
            },null)
        },

        getAllUsers: function(callback) {
            $.getJSON("https://ecistaurant.herokuapp.com/users/",function(data){
                callback(data);
            },null)

        },
        getUserByCarne: function(usuario, callback) {

            $.getJSON("https://ecistaurant.herokuapp.com/users"+usuario,function(data){
                callback(data);
            },null)
        },
        getMenuByRestaurant : function (restaurante,callback) {

            $.getJSON("https://ecistaurant.herokuapp.com/restaurants/"+restaurante+"/menus",function (data) {
                callback(data);
            }, null)
        }
    }

})();