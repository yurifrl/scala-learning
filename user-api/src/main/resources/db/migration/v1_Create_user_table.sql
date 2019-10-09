create table "users" (
    id uuid primary key not null,
    name text not null,
    age int not null,
    occupation text,
    created_at timestamp with time zone not null
);