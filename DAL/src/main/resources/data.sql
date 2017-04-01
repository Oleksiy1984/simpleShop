insert into client(id,user_name,password,role) values(1,'user','1','ROLE_USER');
insert into client(id,user_name,password,role) values(2,'admin','2','ROLE_ADMIN');
insert into manufacturer values (1,'Asus');
insert into manufacturer values (2,'Lenovo');
insert into manufacturer values (3,'Apple');
insert into manufacturer values (4,'Dell');
insert into manufacturer values (5,'Acer');
insert into manufacturer values (6,'Msi');
insert into manufacturer values (7,'HP');
insert into laptop (id,ram,cpu,screen,image,price,status,quantity,id_manufacturer)
values (1,16,'Intel Core i3-6300HQ','15.5-inch (1920x1080) Full HD',
'/resources/image/laptop1.jpg',40000,false,0,1);

insert into laptop (id,ram,cpu,screen,image,price,status,quantity,id_manufacturer)
values (2,32,'Intel Core i5-6300HQ','15.5-inch (1920x1080) Full HD',
'/resources/image/laptop2.jpg',50000,true,6,2);

insert into laptop (id,ram,cpu,screen,image,price,status,quantity,id_manufacturer)
values (3,8,'Intel Core i3-6300HQ','15.5-inch (1920x1080) Full HD',
'/resources/image/laptop3.jpg',30000,true,6,1);

insert into laptop (id,ram,cpu,screen,image,price,status,quantity,id_manufacturer)
values (4,16,'AMD A10-9600P','15.5-inch (1920x1080) Full HD',
'/resources/image/laptop4.jpg',36000,true,2,2);
