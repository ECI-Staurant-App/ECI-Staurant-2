
--MESAS---
insert into mesa values (1,true,'kiosko1',null);
insert into mesa values (2,true,'kiosko1',null);
insert into mesa values (3,false,'kiosko2',null);
insert into mesa values (4,true,'restaurante',null);
insert into mesa values (5,true,'restaurante',null);
insert into mesa values (6,true,'restaurante',null);
insert into mesa values (7,true,'kiosko2',null);
insert into mesa values (8,false,'kiosko2',null);
insert into mesa values (9,false,'kiosko2',null);


---RESTAURANTES---
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'kiosko1','https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80');
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'kiosko2','https://grupolamusa.com/media/grupo-la-musa-hd-madrid-2.jpg');
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'restaurante','https://cdn.hipwallpaper.com/i/26/28/oFCSY3.jpg');

---usuarioS
insert into usuario values (2145195,'johan.arias@mail',true,'johan','johan123',10000);
insert into usuario values (2145191,'test.arias@mail',true,'test','johan123',10000);
insert into usuario values (2145192,'testyy.arias@mail',true,'probando','johan123',10000);
insert into usuario values (2145193,'test5.arias@mail',true,'test2','johan123',10000);
insert into usuario values (2145194,'test1.arias@mail',true,'test3','johan123',0);
insert into usuario values (2145197,'test6.arias@mail',true,'test2','',10000);
insert into usuario values (2145196,'test7.arias@mail',true,'test3','$2a$10$Yr4F8CDjptZ5FWD6xxuTa.qk9Ylvejwccarr8UerQP/heBqmoMcy6',0);
insert into usuario values (1111111,'admin.arias@mail',true,'admin','$2a$10$l5w6NRox6Z0eKlKjcrmzte79ka3T.8iNt6YboZ398h2z0q4SZMvgi',1000000);
insert into usuario values (2199999,'kiosko1',true,'kiosko1','$2a$10$l.sCXWauFzf//kaYTCMXEev.p9pCVE8jDf7mb0jrMHmSAjKoxaHw6',0);
insert into usuario values (2199990,'kiosko2',true,'kiosko2','$2a$10$/yHAc/NgpbEEGBnDj01q2.sVrDEKKyazaZ/.FRqd0UPmsX0TMMgO6',0);
insert into usuario values (2199998,'restaurante',true,'restaurante','$2a$10$8PdQhxpU39Sg3qaR/S0oyO9sO/CptVshX.uXYoHE48YQ2383qUmum',0);
--MENU
insert into menu values (123,'Bandeja paisa',9800,'https://i.pinimg.com/600x315/7d/6e/8f/7d6e8fe02fc717889876b24d0f96fc1a.jpg',1);
insert into menu values (124,'Chuleta valluna',9800,'https://static.wixstatic.com/media/7ad47b_def5d558283e4bb19d450db95545da75~mv2.jpg/v1/fill/w_480,h_319,al_c,q_85,usm_0.66_1.00_0.01/7ad47b_def5d558283e4bb19d450db95545da75~mv2.jpg',1);
insert into menu values (125,'Sopa de arroz',9800,'https://gbprodcdnimages2.azureedge.net/files/styles/recipe_main_image_670x420/windowsazurestorage/recipes/1540112471dc6a49ca746e90c646f5fa86ee2c0409.jpg',1);
insert into menu values (126,'Crep de pollo',9800,'https://okdiario.com/img/2018/04/30/receta-de-crepes-de-pollo-1.jpeg',2);
insert into menu values (127,'Pechuga',9800,'https://sevilla.abc.es/gurme/wp-content/uploads/sites/24/2016/06/pechugadepollo.jpg',3);
insert into menu values (128,'Churrasco',10000,'https://www.goya.com/media/3542/argentinean-grilled-steaks-with-salsa-criolla.jpg?quality=80',1);
insert into menu values (129,'Carne de cerdo',15800,'https://www.sportlife.es/media/cache/big/upload/images/article/5b3f10b4a3fea541e6dfc628/5b4347dc0de6949769349a93-alimentos-deportivos-carne-cerdo.jpg',3);
insert into menu values (130,'Pollo en salsa',10000,'https://www.lacocinademasito.com/files/pollo-en-salsa-final.jpg',2);
--ROLES 

insert into rol (nombre) values ('ROLE_USER');
insert into rol (nombre) values ('ROLE_ADMIN');



insert into usuario_roles values (2145197,(select id from rol where nombre = 'ROLE_USER'));
insert into usuario_roles values (1111111,(select id from rol where nombre = 'ROLE_ADMIN'));
insert into usuario_roles values (2199999,(select id from rol where nombre = 'ROLE_USER'));
insert into usuario_roles values (2199990,(select id from rol where nombre = 'ROLE_USER'));
insert into usuario_roles values (2199998,(select id from rol where nombre = 'ROLE_USER'));


------DELETEEEEES!!!

delete from usuario_roles;
delete from rol;
delete from usuario;


--TRIGER ESTADO PEDIDO

create function trigger_order_state()
	returns trigger
	language 'plpgsql'
as $BODY$
begin
	new.estado:='nuevo';
	return new;
end;
$BODY$

create trigger order_state
before insert on pedido
	for each row 	
		execute procedure trigger_order_state();
	
	






