# Write your MySQL query statement below
Select en.unique_id , e.name
from Employees as e
Left Join EmployeeUNI as en
on e.id = en.id;