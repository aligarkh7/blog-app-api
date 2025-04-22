CREATE TABLE IF NOT EXISTS comments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    blog_id UUID NOT NULL,
    user_id UUID NOT NULL,
    content    TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    is_deleted BOOLEAN                     DEFAULT FALSE,

    CONSTRAINT fk_comments_blog FOREIGN KEY (blog_id) REFERENCES blogs(id),
    CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(id)
)