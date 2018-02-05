SELECT 
    model, speed, hd
FROM
    pc
WHERE
    (cd = '12' OR cd = '24') AND price < 600