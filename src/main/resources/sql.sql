-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    sid bigint NOT NULL DEFAULT nextval('users_sid_seq'::regclass),
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    password character varying(300) COLLATE pg_catalog."default" NOT NULL,
    email character varying(200) COLLATE pg_catalog."default" NOT NULL,
    role text COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (sid),
    CONSTRAINT email UNIQUE (email),
    CONSTRAINT username UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.categories

-- DROP TABLE IF EXISTS public.categories;

CREATE TABLE IF NOT EXISTS public.categories
(
    id bigint NOT NULL DEFAULT nextval('categories_id_seq'::regclass),
    name character varying(300) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id),
    CONSTRAINT name UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categories
    OWNER to postgres;


-- Table: public.sections

-- DROP TABLE IF EXISTS public.sections;

CREATE TABLE IF NOT EXISTS public.sections
(
    id bigint NOT NULL DEFAULT nextval('sections_id_seq'::regclass),
    name character varying(300) COLLATE pg_catalog."default" NOT NULL,
    category_id bigint,
    CONSTRAINT sections_pkey PRIMARY KEY (id),
    CONSTRAINT section_name UNIQUE (name),
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sections
    OWNER to postgres;


-- Table: public.topics

-- DROP TABLE IF EXISTS public.topics;

CREATE TABLE IF NOT EXISTS public.topics
(
    id bigint NOT NULL DEFAULT nextval('topics_id_seq'::regclass),
    name character varying(300) COLLATE pg_catalog."default" NOT NULL,
    date_time time with time zone NOT NULL,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    section_id bigint,
    count_of_messages bigint,
    CONSTRAINT topics_pkey PRIMARY KEY (id),
    CONSTRAINT topic_name UNIQUE (name),
    CONSTRAINT section_id FOREIGN KEY (section_id)
        REFERENCES public.sections (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT username FOREIGN KEY (username)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.topics
    OWNER to postgres;


-- Table: public.messages

-- DROP TABLE IF EXISTS public.messages;

CREATE TABLE IF NOT EXISTS public.messages
(
    id bigint NOT NULL DEFAULT nextval('messages_id_seq'::regclass),
    date_time timestamp with time zone NOT NULL,
    text text COLLATE pg_catalog."default" NOT NULL,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    topic_id bigint,
    number_in_topic bigint,
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT topic_id FOREIGN KEY (topic_id)
        REFERENCES public.topics (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT username FOREIGN KEY (username)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.messages
    OWNER to postgres;