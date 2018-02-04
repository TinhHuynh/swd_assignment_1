/*
  2.Get 3 customers who bought the most quantities by dates
*/
CREATE PROCEDURE dbo.uspGet3CustomersBoughtMostQuantitiesBydates
@from_date DATETIME = '2018-1-24',
@to_date DATETIME = '2018-1-29'

AS
SELECT TOP 3 customer.id, customer.first_name, customer.last_name, SUM(quantity) AS num_of_order
FROM [order_detail]
JOIN [order] ON [order].customer_id = order_id 
JOIN customer ON customer.id = [order].customer_id
WHERE order_date >= @from_date AND order_date <= @to_date
GROUP BY customer.id, customer.first_name, customer.last_name
HAVING customer.id != 0
ORDER BY COUNT([order].id) DESC
GO

EXEC dbo.uspGet3CustomersBoughtMostQuantitiesBydates @from_date = '2018-1-28', @to_date = '2018-1-29'