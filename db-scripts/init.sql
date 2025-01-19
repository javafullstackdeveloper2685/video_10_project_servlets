CREATE TABLE IF NOT EXISTS player_entity (
    uuid BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    player_type VARCHAR(31) NOT NULL,
    attack_power INT,
    health_power INT,
    exp INT,
    lvl INT,
    PRIMARY KEY (uuid)
);