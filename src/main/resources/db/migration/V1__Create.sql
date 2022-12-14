CREATE TABLE IF NOT EXISTS Entry (
    name         VARCHAR(30) NOT NULL PRIMARY KEY CONSTRAINT valid_name CHECK (name ~* '^[a-zA-Z0-9.-]+$'),
    url          VARCHAR(2048) NOT NULL CONSTRAINT valid_url CHECK (url ~* '^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*'';/?:@&=+$,A-Za-z0-9])+)([).!'';/?:,][[:blank:|:blank:]])?$'),
    date_created TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS Acronym (
    acronym_id   INTEGER PRIMARY KEY AUTO_INCREMENT, /*GENERATED BY DEFAULT AS IDENTITY*/
    abbreviation VARCHAR(10) NOT NULL CONSTRAINT valid_abbreviation CHECK (abbreviation ~* '^[a-zA-Z0-9]+$'),
    brief        VARCHAR(256) NOT NULL,
    description  TEXT NOT NULL,
    date_created TIMESTAMP DEFAULT current_timestamp
);