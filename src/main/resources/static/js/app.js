var services = (function () {
    var api = apiclient;
    var restaurantes;
    var restauranteSeleccionado="";
    var user;
    var selectedUser="";
    var menuSeleccionado = "";
    var Pedi;
    //var zelda = "https://ecistaurant.herokuapp.com";
    var zelda ="http://localhost:8080";
    var precioSelected = "";

    function doMap(restaurante) {
        return restaurante.map(function (rt) {
            return {
                rtId: rt.idRestaurante,
                rtName: rt.nombre,
                rtMenus: rt.menus,
                rtFoto: rt.url,
            }
        })
    }

    function doMapMenus(menu){
        return menu.map(function (rt) {
            return {
                menuId: rt.idMenu,
                menuName: rt.nombre,
                menuPrice: rt.precio,
                menuImg: rt.url,
            }
        })

    }

    function placeOrder(){

        selectedUser = sessionStorage.getItem("selectedUser");
        console.log(selectedUser);
        restauranteSeleccionado = sessionStorage.getItem("restauranteSeleccionado");
        console.log(restauranteSeleccionado);
        menuSeleccionado = sessionStorage.getItem("menuSeleccionado");
        console.log(menuSeleccionado);
        alert("su pedido fue registrado exitosamente");
        api.placeOrder(selectedUser,restauranteSeleccionado,menuSeleccionado);

    }

    function panelConfirmarMesas(){
        console.log("PANELCONFIRMAR");
        var confirm = alertify.confirm("¿Desea reservar una mesa?",null,null).set('labels', {
            ok: 'Si',
            cancel: 'No'
        });

        confirm.set('onok', function () {
            window.location.href = zelda + "/mesas.html";
        });
        confirm.set('oncancel', function () {
            tableServices.setMesaNull();
            window.location.href = zelda + "/confirmOrder.html";
        });
    }

    function getUser() {
        return sessionStorage.getItem("selectedUser");
    }

    function getRestaurant() {
        return sessionStorage.getItem("restauranteSeleccionado");
    }
    function getMenu() {
        return sessionStorage.getItem("menuSeleccionado");
    }
    function getPrecioMenu(){
        return sessionStorage.getItem("precioSelected");
    }

    function setRestauranteSeleccionado(id){
        console.log("IDDDDDREST : "+id);
        restauranteSeleccionado = id;
        sessionStorage.setItem("restauranteSeleccionado", restauranteSeleccionado);
        console.log(restauranteSeleccionado);
        window.location.href = zelda+"/restaurante2.html";
        return true;

    }

    function setUltimoPedido(pedido){
        console.log(pedido);
        var restauranteSeleccionado = sessionStorage.getItem("restauranteSeleccionado");
        sessionStorage.setItem("ultPedido",JSON.stringify(pedido));
        //window.open(zelda+"/estadoPedido.html");
        Pedi = JSON.parse(sessionStorage.getItem("ultPedido"));
        console.log(pedido);
        document.getElementById("idOrden").textContent=Pedi.idPedido;
        document.getElementById("menuPedido").textContent=Pedi.menu.nombre;
        conexion.connectAndSubscribeOrder(Pedi.idPedido,restauranteSeleccionado);
    }
    function setMenuSeleccionado(id){
        menuSeleccionado = id;
        sessionStorage.setItem("menuSeleccionado", menuSeleccionado);
        console.log("USER : "+ getUser());
        var extra = getUser();
        console.log("SALDOOOOO " + apiclient.getSaldo(extra,doNothing));


    }
    function setUserLogged(nombre){
        user=$("#username");
        selectedUser = user.val();
        console.log(selectedUser);
        sessionStorage.setItem("selectedUser",selectedUser);

    }

    function doNothing(hola){
        console.log(hola);
        if(hola < getPrecioMenu()){
            alert("No cuentas con el saldo suficiente");
        }else{
            apiclient.updateSaldo(getUser(), Number(hola)-Number(getPrecioMenu()) );
            panelConfirmarMesas()
        }
    }

    function setPrecio(precio){

        precioSelected = precio;
        sessionStorage.setItem("precioSelected",precioSelected);
        console.log("PRECIO: "+ precioSelected);
    }

    function llenaCarrusel(restaurante){
        var u = sessionStorage.getItem("selectedUser");
        console.log("usuario");
        console.log(u);
        restaurantes = doMap(restaurante);
        console.log(restaurantes);
        //$("#carruselRestaurante").empty();

        for ( i in restaurantes){
            var fila ="";
            var slide ="";
            var nombre = restaurantes[i].rtName;
            var id = restaurantes[i].rtId;
            var foto = restaurantes[i].rtFoto;
            console.log(nombre);
            var it=i;
            var primera='<div class="carousel-item active">';
            var normal ='<div class="carousel-item">';
            var lodemas = '<img src="'+ foto +'" alt="First Slide">' + '<div class="carousel-caption d-none d-md-block"> <button type="button" class="btn btn-warning btn-lg btn-block" href="/restaurante2.html"'+' onclick="services.setRestauranteSeleccionado('+'&quot;' +  nombre + '&quot;'  +')">' + 'Ve Ahora a '+  nombre + '!</button>'+ '<h5 id="idrest">' +'</h5> </div> </div>';
            var primeraSlide = '<li data-target="#myCarousel" data-slide-to=' + it + ' class="active"></li>'
            var otrasSlide= '<li data-target="#myCarousel" data-slide-to='+it+'></li>'
            if (i==0){
                fila = primera + lodemas;
                slide = primeraSlide;
            }
            else{
                fila = normal + lodemas;
                slide= otrasSlide;
            }
            $("#numeroSlides").append(slide);
            $("#carruselRestaurante").append(fila);
        }
    }



    function llenarMenu(menus){
        var menus = doMapMenus(menus);
        var voyEn=0;
        for (i=0;i<menus.length;i++){
            var nombre = menus[i].menuName;
            var precio = menus[i].menuPrice;
            var id = menus[i].menuId;
            var foto = menus[i].menuImg;
            var fila = '<div class="row"> <div class="col-lg-4 col-md-6 mb-4"> <div class="card h-100"> <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a> <div class="card-body"><h4 class="card-title"><a href="#">' + nombre+ '</a></h4><h5>' + precio + '</h5><p class="card-text">' + id + '</p></div><div class="card-footer"><small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small></div></div></div>';
            var primero = '<div class="item carousel-item active"> <div id="sub'+i +'" class="row">';
            var otros = '<div class="item carousel-item"> <div id=sub"'+i +'"class="row">';
            var fin = '</div></div>';
            var card = '<div class="col-sm-3"> <div class="thumb-wrapper"> <div class="img-box"> <img src="'+foto +'" class="img-responsive img-fluid" alt=""> </div> <div class="thumb-content"><h4>'+ nombre + '</h4> <p class="item-price">' + '<span> $'+ precio +'</span></p> <div class="star-rating"> <ul class="list-inline"> <li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li><li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li><li class="list-inline-item"><i class="fa fa-star-o"></i></li></ul></div> <a href="#" class="btn btn-primary" onclick="services.setMenuSeleccionado('+'&quot;' +  nombre + '&quot;'  +') ; services.setPrecio('+'&quot;' +  precio + '&quot;'  +')"> Pide ahora! </a></div></div></div>';
            var carrusel="#myCarousel";
            var subItem = "#sub";
            var op= (i+4)%4;
            console.log(i + "->" + op)
            if (op === 0){
                voyEn=i;
                if (i==0) {
                    card = primero + card;
                }
                else{
                    card = otros + card;
                }

                $("#myCarouselmenu").append(card);
            }
            else{
                card = card;

                if (((i+1)%4==0)){
                    card+=fin;
                }
                $(subItem+voyEn).append(card);
            }

        }
        console.log(menus);
    }



    function cargaDataYConecta(){

        //var Pedi = JSON.parse(sessionStorage.getItem("ultPedido"));
        var u = sessionStorage.getItem("selectedUser");
        api.getLastOrderOfUser(u,setUltimoPedido);
        //Pedi = JSON.parse(sessionStorage.getItem("ultPedido"));
        //console.log(Pedi);

    }

    function funcioneMenus() {

        var restauranteSeleccionado = sessionStorage.getItem("restauranteSeleccionado");
        console.log(restauranteSeleccionado);
        apiclient.getMenuByRestaurant(restauranteSeleccionado,llenarMenu);
    }

    function getSelectedUser(){
        var owo = sessionStorage.getItem("selectedUser");
        return owo;
    }

    function funcione(){
        apiclient.getAllRestaurants(llenaCarrusel);
        console.log($("#username"))

    }
    return {
        funcione:funcione,
        funcioneMenus:funcioneMenus,
        setRestauranteSeleccionado:setRestauranteSeleccionado,
        setUserLogged:setUserLogged,
        setMenuSeleccionado : setMenuSeleccionado,
        getSelectedUser:getSelectedUser,
        cargaDataYConecta:cargaDataYConecta,
        setUltimoPedido:setUltimoPedido,
        setPrecio : setPrecio,
        getRestaurant : getRestaurant,
        getMenu : getMenu,
        getUser : getUser,
        getPrecioMenu : getPrecioMenu,
        placeOrder : placeOrder,
        doNothing : doNothing,
    }

})();
