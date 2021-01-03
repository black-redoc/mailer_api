
CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    email VARCHAR(55),
    password VARCHAR(55)
);

CREATE TABLE mail_content (
    id SERIAL PRIMARY KEY NOT NULL,
    _to VARCHAR(55),
    _from VARCHAR(55),
    subject VARCHAR(150),
    body VARCHAR(255)
);