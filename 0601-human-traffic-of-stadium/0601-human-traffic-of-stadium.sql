SELECT DISTINCT s.*
FROM Stadium s
JOIN Stadium s1
    ON s.id = s1.id
    OR s.id = s1.id + 1
    OR s.id = s1.id + 2
JOIN Stadium s2
    ON s2.id = s1.id + 1
JOIN Stadium s3
    ON s3.id = s1.id + 2
WHERE s1.people >= 100
  AND s2.people >= 100
  AND s3.people >= 100
ORDER BY s.visit_date;