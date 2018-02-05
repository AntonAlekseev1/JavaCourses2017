SELECT a.model, price 
FROM (SELECT model, price 
 FROM pc 
 UNION
 SELECT model, price 
  FROM laptop
 UNION
 SELECT model, price 
 FROM printer
 ) AS a JOIN 
 product p ON a.model = p.model
WHERE p.maker = 'maker1';