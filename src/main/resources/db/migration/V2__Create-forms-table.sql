CREATE TABLE forms (
    forms_id SERIAL PRIMARY KEY,
    user_id  UUID  NOT NULL,
    type VARCHAR(50) NOT NULL,
    days INT NOT NULL,
    date_from DATE NOT NULL,
    date_to DATE NOT NULL,
    motive TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(userid)
);
