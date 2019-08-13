SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE USERS;
TRUNCATE TABLE MESSAGES;
TRUNCATE TABLE FRIENDS;
TRUNCATE TABLE FRIENDS;
TRUNCATE TABLE MESSAGES_SENT;
TRUNCATE TABLE MESSAGES_RECEIVED ;
SET FOREIGN_KEY_CHECKS = 1;

insert into USERS (id, user_name, mail, phone, name, last_name) values(21,'arc90','arc_90@coldmail.com',555297462,'Arcangel','Alberti');
insert into USERS (id, user_name, mail, phone, name, last_name) values(22,'beblie16','bel.buar@coldmail.com',555107462,'Beatrice','Buard');
insert into USERS (id, user_name, mail, phone, name, last_name) values(23,'charlie_magic','charlie_magic@coldmail.com',555108172,'Carlos','Rdrgz');
insert into USERS (id, user_name, mail, phone, name, last_name) values(24,'ralph85','ralphWiggum@coldmail.com',555112122,'Rafael','Tomasi');
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(50,'prueba1','Esto es el primer mensaje de prueba','2019-05-17',21,null);
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(51,'prueba2','Esto es el segundo mensaje de prueba','2019-05-19',21,22);
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(52,'mensaje prueba3','Esto es el tercero de prueba','2019-05-23',21,23);
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(53,'mensaje prueba4','Esto es el venticatorce de prueba','2019-05-27',21,22);
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(54,'mensaje prueba4','Esta es la prueba de borrado','2019-05-27',21,null);

insert into MESSAGES_SENT  (users_id, messages_id) values(21,50);
insert into MESSAGES_SENT  (users_id, messages_id) values(21,51);
insert into MESSAGES_SENT  (users_id, messages_id) values(21,52);
insert into MESSAGES_SENT  (users_id, messages_id) values(21,53);
insert into MESSAGES_SENT  (users_id, messages_id) values(21,54);
insert into FRIENDS  (id, friend_id) values(21,23);