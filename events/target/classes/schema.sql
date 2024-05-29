CREATE TABLE IF NOT EXISTS Event (
    id INT NOT NULL,
    title varchar(256) NOT NULL,
    startingAt timestamp NOT NULL,
    finishesAt timestamp NOT NULL,
    description varchar(256) NULL,
    location varchar(10) NOT NULL,
    PRIMARY KEY (id)
);