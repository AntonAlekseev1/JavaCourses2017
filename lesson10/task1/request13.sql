SELECT 
    AVG(speed)
FROM
    product
        JOIN
    pc ON product.model = pc.model
WHERE
    product.maker = 'maker1'