var services = (function () {
    var api = apiclient;
    var restaurantes;
    var restauranteSeleccionado="";
    var user;
    var selectedUser="";
    var menuSeleccionado = "";
    var zelda = "https://ecistaurant.herokuapp.com";

    function placeOrder(){
        selectedUser = sessionStorage.getItem("selectedUser");
        restauranteSeleccionado = sessionStorage.getItem("restauranteSeleccionado");
        return api.placeOrder(selectedUser,restauranteSeleccionado,menuSeleccionado);
    }
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

    function setRestauranteSeleccionado(id){
        console.log("IDDDDDREST : "+id);
        restauranteSeleccionado = id;
        sessionStorage.setItem("restauranteSeleccionado", restauranteSeleccionado);
        console.log(restauranteSeleccionado);
        window.location.href = zelda+"/restaurante2.html";
        return true;

    }
    function setMenuSeleccionado(id){
        console.log("IDDDDDMENU : "+id);
        menuSeleccionado = id;
        sessionStorage.setItem("menuSeleccionado", menuSeleccionado);
        placeOrder();
        console.log(menuSeleccionado);
        alert("Su pedido fue registrado exitosamente!")
    }

    function setUserLogged(nombre){
        user=$("#username");
        selectedUser = user.val();
        console.log(selectedUser);
        sessionStorage.setItem("selectedUser",selectedUser);

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
            var card = '<div class="col-sm-3"> <div class="thumb-wrapper"> <div class="img-box"> <img src="'+foto +'" class="img-responsive img-fluid" alt=""> </div> <div class="thumb-content"><h4>'+ nombre + '</h4> <p class="item-price">' + '<span> $'+ precio +'</span></p> <div class="star-rating"> <ul class="list-inline"> <li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li><li class="list-inline-item"><i class="fa fa-star"></i></li> <li class="list-inline-item"><i class="fa fa-star"></i></li><li class="list-inline-item"><i class="fa fa-star-o"></i></li></ul></div> <a href="#" class="btn btn-primary" onclick="services.setMenuSeleccionado('+'&quot;' +  nombre + '&quot;'  +')"> Pide ahora! </a></div></div></div>';
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

    function funcioneMenus() {
        console.log(restauranteSeleccionado);
        var restauranteSeleccionado = sessionStorage.getItem("restauranteSeleccionado");
        apiclient.getMenuByRestaurant(restauranteSeleccionado,llenarMenu);
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
    }

})();
