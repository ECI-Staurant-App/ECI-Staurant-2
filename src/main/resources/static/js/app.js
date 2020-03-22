var services = (function () {
    var api = apiclient;
    var placeOrder = function(){
        var postPromise = $.ajax({
            url: "/users/AddOrder",
            type: 'POST',
            data: JSON.stringify(selectedBlueprint),
            contentType: "application/json"
        });

        postPromise.then(
            function(){
                console.info('OK');
            },
            function(){
                console.info('NOK');
            }
        );
        return postPromise;
    }
    return {

    }

})();
