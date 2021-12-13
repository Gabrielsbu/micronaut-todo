create table IF NOT EXISTS todos (
    id serial primary key,
    description text,
    done boolean
);

