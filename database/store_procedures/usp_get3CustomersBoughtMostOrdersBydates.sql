USE fruit_shop
GO

/*
  1.Get 3 customers who bought the most orders by dates
*/
CREATE PROCEDURE dbo.uspGet3CustomersBoughtMostOrdersBydates
@from_date DATETIME = '2018-1-24',
@to_date DATETIME = '2018-1-29'

AS
SELECT TOP 3 customer_id, customer.first_name, customer.last_name, COUNT([order].id) AS num_of_order
FROM [order]
JOIN customer ON customer.id = customer_id
WHERE order_date >= @from_date AND order_date <= @to_date
GROUP BY customer_id, customer.first_name, customer.last_name
HAVING customer_id != 0
ORDER BY COUNT([order].id) DESC
GO

EXEC dbo.uspGet3CustomersBoughtMostOrdersBydates @from_date = '2018-1-26'




