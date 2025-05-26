-- Consulta com horários ocupados e livres
WITH time_slots AS (
  SELECT '08:00:00' AS start_time, '09:00:00' AS end_time UNION
  SELECT '09:00:00', '10:00:00' UNION
  SELECT '10:00:00', '11:00:00' UNION
  SELECT '11:00:00', '12:00:00' UNION
  SELECT '13:00:00', '14:00:00' UNION
  SELECT '14:00:00', '15:00:00' UNION
  SELECT '15:00:00', '16:00:00' UNION
  SELECT '16:00:00', '17:00:00' UNION
  SELECT '17:00:00', '18:00:00'
),
days AS (
  SELECT 'Monday' AS day_of_week UNION
  SELECT 'Tuesday' UNION
  SELECT 'Wednesday' UNION
  SELECT 'Thursday' UNION
  SELECT 'Friday'
),
room_schedule_grid AS (
  SELECT 
    r.id AS room_id,
    r.building_id,
    d.day_of_week,
    t.start_time,
    t.end_time
  FROM ROOM r
  CROSS JOIN days d
  CROSS JOIN time_slots t
),
occupied_slots AS (
  SELECT 
    cs.room_id,
    cs.day_of_week,
    cs.start_time,
    cs.end_time
  FROM CLASS_SCHEDULE cs
)
SELECT 
  g.room_id,
  g.day_of_week,
  g.start_time,
  g.end_time,
  CASE 
    WHEN o.room_id IS NOT NULL THEN 'Ocupado'
    ELSE 'Livre'
  END AS status
FROM room_schedule_grid g
LEFT JOIN occupied_slots o
  ON g.room_id = o.room_id
  AND g.day_of_week = o.day_of_week
  AND g.start_time = o.start_time
  AND g.end_time = o.end_time
ORDER BY g.room_id, 
         FIELD(g.day_of_week, 'Monday','Tuesday','Wednesday','Thursday','Friday'),
         g.start_time;
