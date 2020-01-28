CREATE TABLE film (
    id                INTEGER,
    naziv             VARCHAR (30) NOT NULL,
    reziser           VARCHAR (30),
    glumci            VARCHAR (50),
    zanrovi           VARCHAR (20) NOT NULL,
    trajanje          INT          NOT NULL,
    distributer       VARCHAR (30) NOT NULL,
    zemljaPorekla     VARCHAR (30) NOT NULL,
    godinaProizvodnje INT          NOT NULL,
    opis              VARCHAR (60),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE karta (
    id           INTEGER,
    projekcija   STRING  NOT NULL,
    sediste      INT     NOT NULL,
    datumProdaje DATE    NOT NULL,
    vremeProdaje STRING  NOT NULL,
    korisnik     STRING  NOT NULL,
    PRIMARY KEY (
        id
    )
);
CREATE TABLE korisnik (
    id            INTEGER,
    korisnickoIme VARCHAR (10) NOT NULL,
    lozinka       VARCHAR (10) NOT NULL,
    datumReg      DATETIME     NOT NULL,
    uloga         VARCHAR (10) NOT NULL,
    PRIMARY KEY (
        id
    )
);
CREATE TABLE projekcija (
    id                INTEGER PRIMARY KEY,
    film              STRING  NOT NULL,
    tipProjekcije     STRING  NOT NULL,
    sala              STRING  NOT NULL,
    datumPrikazivanja DATE    NOT NULL,
    vremePrikazivanja STRING  NOT NULL,
    cenaKarte         DOUBLE  NOT NULL,
    admin             STRING  NOT NULL
);
CREATE TABLE sala (
    id            INTEGER PRIMARY KEY,
    naziv         STRING  NOT NULL,
    tipProjekcije STRING  NOT NULL
);
CREATE TABLE sediste (
    id        INTEGER PRIMARY KEY,
    redniBroj INTEGER NOT NULL,
    sala      STRING  NOT NULL
);
CREATE TABLE tipProjekcije (
    id    INTEGER PRIMARY KEY,
    naziv STRING  NOT NULL
);
CREATE TABLE izvestaj (
    id              INTEGER PRIMARY KEY,
    nazivFilma      STRING  NOT NULL,
    brojProjekcija  INTEGER NOT NULL,
    brojKarata      INTEGER NOT NULL,
    ukupnaCenaKarata DOUBLE  NOT NULL
);
