# Write your MySQL query statement below
Select e.name , b.bonus
from Employee e
Left Join Bonus b
on e.empId=b.empId
where b.bonus is NULL or b.bonus <1000;