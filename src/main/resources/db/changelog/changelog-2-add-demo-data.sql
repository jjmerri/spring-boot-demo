--liquibase formatted sql
--changeset jjmerri:2-add-demo-data
--this is to have sample data to query

INSERT INTO CUSTOMER (name)
VALUES ('Bobby');
INSERT INTO CUSTOMER (name)
VALUES ('Peter');
INSERT INTO CUSTOMER (name)
VALUES ('Greg');

INSERT INTO ACCOUNT (customer_id, type)
VALUES (1, 'PERSONAL');
INSERT INTO ACCOUNT (customer_id, type)
VALUES (1, 'BUSINESS');
INSERT INTO ACCOUNT (customer_id, type)
VALUES (2, 'BUSINESS');

