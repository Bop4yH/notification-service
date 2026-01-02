CREATE TABLE transfer_counter (
    user_id UUID PRIMARY KEY,
    transfer_count BIGINT DEFAULT 0
);

CREATE TABLE achievements (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    awarded_at TIMESTAMP,
    UNIQUE(user_id, name)
);