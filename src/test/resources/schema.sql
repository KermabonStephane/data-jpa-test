CREATE TABLE employee (
    id          UUID NOT NULL,
    firstname   VARCHAR(255) NOT NULL,
    lastname    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);