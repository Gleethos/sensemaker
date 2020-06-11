
DROP TABLE IF EXISTS pictures
--<#SPLIT#>--
DROP TABLE IF EXISTS photographers;
--<#SPLIT#>--
DROP TABLE IF EXISTS IPTCs;
--<#SPLIT#>--
DROP TABLE IF EXISTS EXIFs
--<#SPLIT#>--
CREATE TABLE EXIFs(
    id INTEGER NOT NULL PRIMARY KEY,
    shot DATE NULL,
    orientation TEXT NULL,
    created DATE NOT NULL,
    deleted Date NULL
);
--<#SPLIT#>--
CREATE TABLE IPTCs(
    id INTEGER NOT NULL PRIMARY KEY,
    title TEXT NULL,
    description TEXT NULL,
    copyright TEXT NULL,
    keywords TEXT NULL,
    created DATE NOT NULL,
    deleted Date NULL
);
--<#SPLIT#>--
CREATE TABLE photographers(
    id INTEGER NOT NULL PRIMARY KEY,
    forename TEXT NOT NULL,
    surname TEXT NULL,
    created DATE NOT NULL,
    deleted Date NULL
);
--<#SPLIT#>--
CREATE TABLE pictures(
    id INTEGER NOT NULL PRIMARY KEY,
    path TEXT NULL,
    created DATE NOT NULL,
    deleted Date NULL,
    EXIF_id INTEGER NULL,
    IPTC_id INTEGER NULL,
    photographer_id INTEGER NULL,
    FOREIGN KEY (EXIF_id) REFERENCES EXIFs(id),
    FOREIGN KEY (IPTC_id) REFERENCES IPTCs(id),
    FOREIGN KEY (photographer_id) REFERENCES photographers(id)
);