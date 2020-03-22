
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
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'kiosko1');
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'kiosko2');
insert into restaurante values ((select count(id_restaurante) from restaurante )+1,'restaurante');

---usuarioS
insert into usuario values (2145195,'johan.arias@mail',true,'johan','johan123',10000);
insert into usuario values (2145191,'test.arias@mail',true,'test','johan123',10000);
insert into usuario values (2145192,'testyy.arias@mail',true,'probando','johan123',10000);
insert into usuario values (2145193,'test5.arias@mail',true,'test2','johan123',10000);
insert into usuario values (2145194,'test1.arias@mail',true,'test3','johan123',0);
insert into usuario values (2145197,'test6.arias@mail',true,'test2','',10000);
insert into usuario values (2145196,'test7.arias@mail',true,'test3','$2a$10$Yr4F8CDjptZ5FWD6xxuTa.qk9Ylvejwccarr8UerQP/heBqmoMcy6',0);
insert into usuario values (1111111,'admin.arias@mail',true,'admin','$2a$10$l5w6NRox6Z0eKlKjcrmzte79ka3T.8iNt6YboZ398h2z0q4SZMvgi',1000000);

--MENU
insert into menu values (123,'Bandeja paisa',9800,1);
insert into menu values (124,'Chuleta paisa',9800,1);
insert into menu values (125,'Sopa de arroz',9800,1);
insert into menu values (126,'Bandeja paisa',9800,2);
insert into menu values (127,'Pechuga',9800,3);
insert into menu values (128,'Churrasco',10000,1);
insert into menu values (129,'Bandeja paisa',15800,3);
insert into menu values (130,'Bandeja pastusa',10000,2);

--ROLES 

insert into rol (nombre) values ('ROLE_USER');
insert into rol (nombre) values ('ROLE_ADMIN');



insert into usuario_roles values (2145197,(select id from rol where nombre = 'ROLE_USER'));
insert into usuario_roles values (1111111,(select id from rol where nombre = 'ROLE_ADMIN'));


------DELETEEEEES!!!

delete from usuario_roles;
delete from rol;
delete from usuario;







