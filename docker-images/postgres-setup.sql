CREATE TABLE IF NOT EXISTS arrow
(
    id             INT           NOT NULL,
    first_name     VARCHAR(31)   NOT NULL,
    last_name      VARCHAR(31)   NOT NULL,
    age            INT           NOT NULL,
    phone_numbers  VARCHAR(15)[] NOT NULL,
    income         REAL          NOT NULL,
    house_location VARCHAR(255)  NOT NULL
);