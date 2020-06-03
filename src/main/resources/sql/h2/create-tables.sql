CREATE TABLE IF NOT EXISTS test.objects (
    id INT PRIMARY KEY,
    name varchar2(255)
);

CREATE SEQUENCE IF NOT EXISTS test.objects_pk_seq
    START WITH 1
    INCREMENT BY 1;