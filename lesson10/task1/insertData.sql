USE comp_firm;

insert into product(maker,model,type) 
values ('maker1','pc1','PC'),('maker1','pc2','PC'),('maker1','pc6','PC'),
('maker2','pc3','PC'),('maker2','pc4','PC'),('maker1','pc5','PC'),('maker1','pc7','PC'),
('maker3','lt01','Laptop'),('maker3','lt02','Laptop'),
('maker4','lt03','Laptop'),('maker4','lt04','Laptop'),
('maker1','lt05','Laptop'),
('maker5','hp1','Printer'),('maker5','cn2','Printer'),
('maker6','hp2','Printer');

insert into pc (code, model, speed, ram, hd, cd, price)
         values (1, 'pc1', 450, 2000, 250, '24', 300.0),
                (2, 'pc2', 800, 4000, 500, '12', 600.0),
				(3, 'pc3', 500, 500, 80, '4',150.0),
                (4, 'pc4', 1200, 8000, 1000, '48', 1500.0),
                (5, 'pc5', 350, 2000, 500, '12', 800.0),
                (6, 'pc6', 450, 2000, 250, '24', 300.0),
                (7, 'pc7', 2800, 300, 250, '24', 300.0);

insert into laptop(code,model,speed,ram,hd,screen, price)
values (1, 'lt01', 300, 512, 80, 16, 500),
	   (2, 'lt02', 450, 1000, 250, 19, 1000),
       (3, 'lt03', 1200, 2000, 1000, 19, 1500),
       (4, 'lt04', 200, 250, 8, 14, 200),
       (5, 'lt05', 1200, 1200, 500, 16, 1000);

insert into printer(code, model, color, type, price)
values (1, 'hp1', 'n', 'jet', 100),
(2, 'cn2', 'y', 'jet', 200),
(3, 'hp2', 'n', 'laser', 300);

commit;