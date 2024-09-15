  CREATE TABLE users (
    userId UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    userName VARCHAR(30) NOT NULL,
    email VARCHAR(40) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    role Varchar NOT NULL
);