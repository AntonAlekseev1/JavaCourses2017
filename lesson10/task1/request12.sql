SELECT 
    AVG(speed) AS avg_speed
FROM
    laptop
WHERE
    speed IN (SELECT 
            speed
        FROM
            laptop
        WHERE
            price > 1000)