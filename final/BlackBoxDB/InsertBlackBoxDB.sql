use blackbox;
#Users insert
insert into users(login,password,name,last_name,role,birthday)
values('anton','484616813e169928eb8258b83c5f7107','Anton','Alekseev','ADMIN','1993-01-24'), #admin
('misha','94f4b428e5b34f6dd1b0404827ff25bd','Misha','Akrapovich','USER','1993-03-15'), #1q2w
('artur','36bca54de1cc2975c054f19fbc9f9b8f','Artur','Romanchuk','USER','1994-04-21'), #123
('anya','270a4a5421226e46ac13eb08527aacad','Anya','Juk','USER','1995-07-11'),     #321
('lena','6e1d72698f041e18b895105f8e58276e','Lena','Belko','USER','1996-04-21'); #qwerty

#Group insert
insert into groups(name, creation_date, admin)
values('java_group', '2017-09-20', 1), ('php_group', '2017-06-12',2), ('Angular_group', '2018-03-12', 1);

#Group_users insert
insert into group_users(group_id, user_id)
values(1,1),(1,2),(1,3),(2,1),(2,3),(3,1);

#Events insert
insert into events(name, body, date, group_id, user_id)
values('hello','Hello World','2018-04-29',null,1), ('test','Test insert','2018-04-29',1,null);



