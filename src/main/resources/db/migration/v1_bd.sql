-- Define the 'rol' table
CREATE TABLE public.rol (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Define the 'user_detail' table
CREATE TABLE public.user_detail (
    id SERIAL PRIMARY KEY,
    age INT,
    birth_day DATE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_id INT UNIQUE,
    CONSTRAINT user_detail_fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id)
);

-- Define the 'user_rol' table
CREATE TABLE public.user_rol (
    id SERIAL PRIMARY KEY,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    rol_id INT,
    user_id INT,
    CONSTRAINT user_rol_fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id),
    CONSTRAINT user_rol_fk_rol_id FOREIGN KEY (rol_id) REFERENCES public.rol(id)
);

-- Define the 'users' table
CREATE TABLE public.users (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    username VARCHAR(150) NOT NULL
);
