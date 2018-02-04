/*
3.print order by order id
*/
USE fruit_shop
GO

CREATE PROCEDURE dbo.usp_printOrderByOrderId
@order_id INT
AS
BEGIN

DECLARE @order_date DATETIME
DECLARE @staff_first_name NVARCHAR(20)
DECLARE @staff_last_name NVARCHAR(20)
DECLARE @customer_first_name NVARCHAR(20)
DECLARE @customer_last_name NVARCHAR(20)
DECLARE @total_money FLOAT

-- print basic information --
SELECT TOP 1  
		@order_date = order_date,
		@staff_first_name = staff.first_name,
		@staff_last_name = staff.last_name,
		@customer_first_name = customer.first_name,
		@customer_last_name = customer.last_name,
		@total_money = SUM(order_detail.quantity*order_detail.price)
FROM [order]
JOIN staff ON staff.id = staff_id
JOIN customer ON customer.id = customer_id
JOIN order_detail ON order_detail.order_id = [order].id
WHERE [order].id = @order_id
GROUP BY [order].id, order_date, staff.first_name, staff.last_name, customer.first_name, customer.last_name

PRINT N'Order date: ' + CONVERT(VARCHAR(19), @order_date, 120);
PRINT N'Staff: ' + @staff_last_name + ' ' + @staff_first_name;  
PRINT N'Customer: ' + @customer_last_name + ' ' + @customer_first_name;

-- print detail --
PRINT N'=========================================================================================================================='
PRINT N'Product name                 Quantity           Price           Money'

PRINT N'=========================================================================================================================='

DECLARE @product_name NVARCHAR(100)
DECLARE @quantity FLOAT
DECLARE @price FLOAT
DECLARE @detail_total_money FLOAT
DECLARE @order_detail_id INT

DECLARE DetailCursor CURSOR FOR
SELECT 
	product.name, 
	quantity, 
	price, 
	SUM(quantity * price),
	order_detail.id
FROM order_detail
JOIN [order] ON order_detail.order_id = [order].id
JOIN [product] ON product.id = product_id
WHERE [order].id = @order_id
GROUP BY product.name, quantity, price, order_detail.id

OPEN DetailCursor
FETCH NEXT FROM DetailCursor INTO @product_name, @quantity, @price, @detail_total_money, @order_detail_id
	WHILE @@FETCH_STATUS = 0
		BEGIN
		PRINT @product_name + '                 ' + CAST(@quantity AS VARCHAR) + '           ' + CAST(@price AS VARCHAR) + '           ' + CAST(@detail_total_money AS VARCHAR);   
		FETCH NEXT FROM DetailCursor INTO @product_name, @quantity, @price, @detail_total_money, @order_detail_id
		END
CLOSE DetailCursor
DEALLOCATE DetailCursor


-- print total money --
PRINT N'--------------------------------------------------------------------------------------------------------------------------'
PRINT N'Total money: ' + CAST(@total_money AS VARCHAR); 
  

END
GO

EXEC dbo.usp_printOrderByOrderId 33

