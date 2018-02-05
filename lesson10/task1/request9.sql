SELECT DISTINCT
    Product.maker
FROM
    Product
        JOIN
    pc ON Product.model = pc.model
WHERE
    speed >= 450