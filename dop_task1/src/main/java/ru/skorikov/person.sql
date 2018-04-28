
CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);


INSERT INTO person values
(1,'a',1),(2,'aa',1),(3,'aaa',1),(4,'aaaa',1),(5,'aaaaa',1),
(6,'g',2),(7,'gg',2),(8,'ggg',2),(9,'gggg',2),(10,'ggggg',2),
(11,'m',3),(12,'mm',3),(13,'mmm',3),(14,'mmmm',3),(15,'mmmmm',3),
(16,'i',4),(17,'ii',4),(18,'iii',4),(19,'iiii',4),(20,'iiiii',4),
(21,'xxxxx',5),(22,'xxxxx',5),(23,'xxxxx',5),(24,'xxxxx',5),(25,'xxxxx',5),(31,'xxxxx',5),(32,'xxxxx',5),
(26,'r',6),(27,'rr',6),(28,'rrr',6),(29,'rrrr',6),(30,'rrrrr',6);