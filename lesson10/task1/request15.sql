SELECT 
    hd
FROM
    pc
GROUP BY hd
HAVING COUNT(hd) > 1