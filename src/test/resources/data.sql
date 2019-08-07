SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE USERS;
TRUNCATE TABLE MESSAGES;
TRUNCATE TABLE FRIENDS;
TRUNCATE TABLE FRIENDS;
TRUNCATE TABLE MESSAGES_SENT;
TRUNCATE TABLE MESSAGES_RECEIVED ;
SET FOREIGN_KEY_CHECKS = 1;

insert into USERS (user_name, mail, phone, name, last_name) values('arc90','arc_90@coldmail.com',555297462,'Arcangel','Alberti');
insert into USERS (user_name, mail, phone, name, last_name) values('beblie16','bel.buar@coldmail.com',555107462,'Beatrice','Buard');
insert into USERS (user_name, mail, phone, name, last_name) values('charlie_magic','charlie_magic@coldmail.com',555108172,'Carlos','Rdrgz');
insert into USERS (user_name, mail, phone, name, last_name) values('ralph85','ralphWiggum@coldmail.com',555112122,'Rafael','Tomasi');
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(50,'prueba1','Esto es el primer mensaje de prueba','2019-05-17','arc90',null);
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(51,'prueba2','Esto es el segundo mensaje de prueba','2019-05-19','arc90','beblie16');
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(52,'mensaje prueba3','Esto es el tercero de prueba','2019-05-23','arc90','charlie_magic');
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(53,'mensaje prueba4','Esto es el venticatorce de prueba','2019-05-27','arc90','beblie16');
insert into MESSAGES (id,title, body, date, messages_owner,messages_destination) values(54,'mensaje prueba4','Esta es la prueba de borrado','2019-05-27','arc90',null);

insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',50);
insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',51);
insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',52);
insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',53);
insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',54);
insert into FRIENDS  (user_name, friend_id) values('arc90','charlie_magic');