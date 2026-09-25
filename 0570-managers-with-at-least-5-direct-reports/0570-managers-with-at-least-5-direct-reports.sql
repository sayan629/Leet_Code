SELECT e.name
FROM Employee e
INNER JOIN Employee m
    ON e.id = m.managerId
GROUP BY m.managerId
HAVING COUNT(e.id) >= 5;