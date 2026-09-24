# Write your MySQL query statement below
Select a1.machine_id, Round(AVG(a2.timestamp-a1.timestamp),3)
as processing_time
From Activity a1
Inner join Activity a2
on a1.process_id = a2.process_id
AND a1.machine_id = a2.machine_id
AND a1.activity_type = 'start'
AND a2.activity_type = 'end'
group by a1.machine_id;