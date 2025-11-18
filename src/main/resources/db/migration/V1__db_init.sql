-- Table: app_configurations
CREATE TABLE IF NOT EXISTS app_configurations (
    vapi_private_key VARCHAR(100),
    vapi_public_key VARCHAR(100),
    keycloak_admin_email VARCHAR(100),
    keycloak_admin_password VARCHAR(100),
    type VARCHAR(100),
    keycloak_client_id VARCHAR(100),
    keycloak_realm_name VARCHAR(100),
    keycloak_url VARCHAR(100),
    frontend_url VARCHAR(250)
);

-- Table: assistant
CREATE TABLE IF NOT EXISTS assistant (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    assistant_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    voice_id BIGINT NOT NULL,
    model_id BIGINT NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    UNIQUE KEY assistant_unique (assistant_id)
);

-- Table: call
CREATE TABLE IF NOT EXISTS `call` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    call_id VARCHAR(250) NOT NULL,
    duration INT DEFAULT NULL,
    status VARCHAR(100) NOT NULL,
    cost DOUBLE DEFAULT NULL,
    started_at VARCHAR(100),
    ended_at VARCHAR(100),
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id VARCHAR(100) NOT NULL,
    UNIQUE KEY call_unique (call_id)
);

-- Table: commonlist
CREATE TABLE IF NOT EXISTS commonlist (
    text VARCHAR(250) NOT NULL,
    value VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    UNIQUE KEY commonlist_unique (type)
    );

-- Table: document
CREATE TABLE IF NOT EXISTS document (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    url VARCHAR(250) NOT NULL,
    size INT NOT NULL,
    document_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id VARCHAR(100) NOT NULL,
    UNIQUE KEY document_unique (document_id)
);

-- Table: knowledgebase
CREATE TABLE IF NOT EXISTS knowledgebase (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tool_id VARCHAR(100) NOT NULL,
    assistant_id VARCHAR(100) NOT NULL,
    UNIQUE KEY knowledgebase_unique (tool_id),
    UNIQUE KEY knowledgebase_unique_1 (assistant_id)
);

-- Table: model
CREATE TABLE IF NOT EXISTS model (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    provider VARCHAR(100) NOT NULL,
    value VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- Table: voice
CREATE TABLE IF NOT EXISTS voice (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tone VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    accent VARCHAR(100) NOT NULL,
    gender VARCHAR(100) NOT NULL,
    voice_id VARCHAR(100) NOT NULL,
    provider VARCHAR(100) NOT NULL,
    UNIQUE KEY voice_unique (voice_id)
);
