apiclient = (function () {

    return {
        getAllUsers: function(callback) {
            $.getJSON("http://localhost:8080/users/",function(data){
                callback(data);
            },null)

        },
        getUserByCarne: function(usuario, callback) {

            $.getJSON("http://localhost:8080/users/"+usuario,function(data){
                callback(data);
            },null)
        }
    }

})();