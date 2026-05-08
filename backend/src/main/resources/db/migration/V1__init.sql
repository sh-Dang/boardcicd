CREATE TABLE members (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    email      VARCHAR(100) NOT NULL,
    nickname   VARCHAR(20)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    username   VARCHAR(50)  NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    PRIMARY KEY (id),
    UNIQUE KEY uq_members_email (email),
    UNIQUE KEY uq_members_username (username)
);

CREATE TABLE posts (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    title      VARCHAR(200) NOT NULL,
    content    TEXT         NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    members_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_posts_member FOREIGN KEY (members_id) REFERENCES members (id)
);