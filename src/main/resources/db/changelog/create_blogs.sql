CREATE TABLE IF NOT EXISTS blogs
(
    id         UUID PRIMARY KEY            DEFAULT uuid_generate_v4(),
    user_id    UUID         NOT NULL,
    title      VARCHAR(255) NOT NULL,
    content    TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    is_deleted BOOLEAN                     DEFAULT FALSE,

    CONSTRAINT fk_blogs_users FOREIGN KEY (user_id) REFERENCES users (id)
);