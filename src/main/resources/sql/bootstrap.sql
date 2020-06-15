
DROP TABLE IF EXISTS pictures
--<#SPLIT#>--
DROP TABLE IF EXISTS photographers;
--<#SPLIT#>--
DROP TABLE IF EXISTS IPTCs;
--<#SPLIT#>--
DROP TABLE IF EXISTS EXIFs
--<#SPLIT#>--
CREATE TABLE EXIFs(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    shot DATE NULL,
    orientation TEXT NULL,
    created DATE NOT NULL,
    deleted DATE NULL
);
--<#SPLIT#>--
CREATE TABLE IPTCs(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    title TEXT NULL,
    description TEXT NULL,
    copyright TEXT NULL,
    keywords TEXT NULL,
    created DATE NOT NULL,
    deleted DATE NULL
);
--<#SPLIT#>--
CREATE TABLE photographers(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    forename TEXT NOT NULL,
    surname TEXT NULL,
    created DATE NOT NULL,
    deleted DATE NULL
);
--<#SPLIT#>--
CREATE TABLE pictures(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    path TEXT NULL,
    created DATE NOT NULL,
    deleted DATE NULL,
    EXIF_id INTEGER NULL,
    IPTC_id INTEGER NULL,
    photographer_id INTEGER NULL,
    FOREIGN KEY (EXIF_id) REFERENCES EXIFs(id),
    FOREIGN KEY (IPTC_id) REFERENCES IPTCs(id),
    FOREIGN KEY (photographer_id) REFERENCES photographers(id)
);