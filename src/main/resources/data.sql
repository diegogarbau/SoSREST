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

insert into MESSAGES (id,title, body, date, messages_owner) values(1,'prueba1','Esto es un mensaje de prueba','2019-05-17','arc90');
insert into MESSAGES_SENT  (users_user_name, messages_id) values('arc90',1);