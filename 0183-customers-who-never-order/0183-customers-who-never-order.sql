# Write your MySQL query statement below
Select name as  Customers from Customers 
Left join
Orders
on Customers.id = orders.customerId
where customerId is null;