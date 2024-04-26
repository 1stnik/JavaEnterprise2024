DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS asks CASCADE;

-- Crate table: users
CREATE TABLE IF NOT EXISTS users
(
    id   INT PRIMARY KEY NOT NULL,
    name VARCHAR(255)       NOT NULL
);

-- Crate table: tasks
CREATE TABLE IF NOT EXISTS tasks
(
    task_Id      INT PRIMARY KEY NOT NULL,
    title       VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    deadline    VARCHAR(50),
    priority    VARCHAR(50)     NOT NULL,
    status      VARCHAR(50)     NOT NULL,
    user_id     INT NULL ,
    FOREIGN KEY (user_id) REFERENCES users (id)
);