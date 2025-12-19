CREATE TABLE IF NOT EXISTS outbox_message(
                                        id BIGSERIAL PRIMARY KEY,
                                        topic VARCHAR(255),
                                        key VARCHAR(255),
                                        payload jsonb,
                                        created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                                        status VARCHAR(50) NOT NULL,
                                        type VARCHAR(255)
);