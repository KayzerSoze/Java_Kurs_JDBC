
/*
 1) Получить в одном запросе:
 - имена всех лиц, которые НЕ принадлежат компании с id = 5
 - название компании для каждого человека
*/

SELECT company.id, person.name, company.name 
FROM person 
JOIN company on person.company_id = company.id 
WHERE company_id != 5;

/*
 2) Выберите название компании с максимальным количеством лиц + количество человек в этой компании
*/

select company.id, company.name, count(person.company_id) as person_count 
FROM company JOIN person on company.id = person.company_id
GROUP BY company.id
ORDER BY count(person.company_id) desc limit 1;