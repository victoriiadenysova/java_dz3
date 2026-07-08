SELECT 'OLDEST' AS type, name, birthday
FROM worker
WHERE birthday = (SELECT MIN(birthday) FROM worker)
UNION ALL
SELECT 'YOUNGEST' AS type, name, birthday
FROM worker
WHERE birthday = (SELECT MAX(birthday) FROM worker)
ORDER BY birthday;
