SELECT 
    p.id AS professor_id,
    ROUND(SUM(TIME_TO_SEC(TIMEDIFF(cs.end_time, cs.start_time)))/3600, 2) AS total_hours
FROM 
    PROFESSOR p
JOIN 
    CLASS_PROFESSOR cp ON p.id = cp.professor_id
JOIN 
    CLASS c ON cp.class_id = c.id
JOIN 
    CLASS_SCHEDULE cs ON c.id = cs.class_id
GROUP BY 
    p.id
ORDER BY 
    total_hours DESC;
