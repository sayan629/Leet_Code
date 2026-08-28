# Write your MySQL query statement below
select id, movie, description, rating from Cinema
where Mod(id,2)<>0 and DESCRIPTION <>'boring'
order by rating desc;