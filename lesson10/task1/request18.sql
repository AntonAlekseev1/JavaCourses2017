SELECT DISTINCT
    maker, price
FROM
    printer
        INNER JOIN
    product ON printer.model = product.model
WHERE
    price = (SELECT 
            MIN(price)
        FROM
            printer
        WHERE
            color = 'y')
        AND color = 'y'