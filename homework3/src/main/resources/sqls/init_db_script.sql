CREATE TABLE study_group
(
    id bigserial PRIMARY KEY NOT NULL,
    name character varying(128) NOT NULL
);
CREATE TABLE study_plan
(
    id bigserial PRIMARY KEY NOT NULL,
    name character varying(128) NOT NULL
);

CREATE TABLE student
(
    id bigserial PRIMARY KEY NOT NULL,
    first_name character varying(45) NOT NULL,
    last_name character varying(45) NOT NULL,
    age integer,
    study_group_id bigserial REFERENCES study_group(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE academic_record
(
    id bigserial PRIMARY KEY NOT NULL,
    assessment integer,
    student_id bigserial REFERENCES student(id) NOT NULL,
    study_plan_id bigserial REFERENCES study_plan(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


--SELECT * FROM
--(SELECT student_id, first_name, last_name, age,  COUNT(*) as count_assessment
--FROM student JOIN academic_record ON student.id = student_id
--WHERE age > 14 AND assessment = 5 GROUP BY student_id, first_name, last_name, age)
--WHERE count_assessment = 6;
--
--SELECT study_plan.name, AVG(assessment) FROM study_group
--JOIN student ON study_group.id = student.study_group_id
--JOIN academic_record ON student.id = academic_record.student_id
--JOIN study_plan ON study_plan.id = academic_record.study_plan_id
--WHERE study_group.name = '10'
--GROUP BY study_plan.name;
--
--
-- SELECT name, first_name, last_name, age, AVG(assessment) FROM study_group JOIN student ON study_group.id = student.study_group_id
-- JOIN academic_record ON student.id = academic_record.student_id
-- WHERE first_name LIKE 'Абрамов'
-- GROUP BY name, first_name, last_name, age;

