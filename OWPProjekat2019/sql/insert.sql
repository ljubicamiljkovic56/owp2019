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

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (1, 'Mr. Bean', 'Mel Smith', 'Rowan Atkinson', 'komedija', 130, 'CinemaCity', 'UK', 1997, 'porodicni film');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (2, 'Kamiondzije', 'Petar Petrovic', 'Miodrag Petrovic Ckalja','komedija', 90, 'BeoFilm', 'SRB', 1972, 'kamiondzije');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (3, 'Sam u kuci', 'Chris Columbus', 'Macaulay Culkin', 'komedija', 143, 'Megacom', 'USA', 1990, 'bozicni film');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (4, 'Mr. Bean na odmoru', 'Steve Bendelack', 'Rowan Atkinson', 'komedija', 90, 'CinemaCity', 'USA', 2007, 'porodicni film');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (5, 'Frozen', 'Anna Sign', 'Idina Menzel', 'animirani', 100, 'FilmDOO', 'USA', 2014, 'lep crtani');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (6, 'The X Files', 'Rob Bowman', 'David Duchovny', 'sci-fi', 200, 'EuroFilm', 'USA', 1998, 'scifi film sa trilerom');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (7, 'Loving Vincent', 'Dorota Kobiela', 'Robert Gulazcyk', 'drama', 130, 'Megacom', 'PL', 2017, 'zivot Van Gogha');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (8, 'Parasite', 'Bong Joo-Ho', 'Cho Yeo-Jeong', 'misterija', 212, 'FilmDOO', 'KR', 2019, 'korejski film');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (9, 'Indiana Jones: Last Crusade', 'Steven Spielberg', 'Harrison Ford', 'avantura', 208, 'EuroFilm', 'USA', 1989, 'avanturisticki film');

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
VALUES (10, 'Rain Man', 'Barry Levison', 'Dustin Hoffman', 'melodrama', 214, 'Megacom', 'USA', 1988, 'dobar film');





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

INSERT INTO karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik)
VALUES (1, 'Mr. Bean', 12, '1580252400000', '18:00', 'pera');

INSERT INTO karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik)
VALUES (2, 'Kamiondzije' , 12, '1580166000000', '13:00', 'ana');

INSERT INTO karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik)
VALUES (3, 'Sam u kuci', 6, '1581807600000', '17:00', 'joca');


INSERT INTO karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik)
VALUES (4, 'Mr. Bean', 7, '1582153200000', '16:00', 'miki');


INSERT INTO karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik)
VALUES (5, 'Mr. Bean', 10, '1578255009000', '21:00', 'ana');



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

INSERT INTO korisnik(id, korisnickoIme, lozinka, datumReg, uloga)
VALUES (1, 'pera', 'pera', '1578524400000', 'admin');

INSERT INTO korisnik(id, korisnickoIme, lozinka, datumReg, uloga)
VALUES (2, 'ana', 'ana', '1578327880000', 'korisnik');

INSERT INTO korisnik(id, korisnickoIme, lozinka, datumReg, uloga)
VALUES (3, 'joca', 'joca', '1580070603000', 'korisnik');

INSERT INTO korisnik(id, korisnickoIme, lozinka, datumReg, uloga)
VALUES (4, 'miki', 'miki', '1578524400000', 'korisnik');



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

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(1, 'Kamiondzije', '2D', 'S1', '1580166000000', '13:00', 120, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(2, 'Mr. Bean', '3D', 'S3', '1580252400000', '18:00', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(3, 'Sam u kuci', '2D', 'S2', '1580338800000', '13:00', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(4, 'Samu u kuci', '3D', 'S3', '1581807600000', '17:00', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(5, 'Mr. Bean', '3D', 'S3', '1581375600000', '19:00', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(6, 'Mr. Bean', '2D', 'S1', '1582153200000', '16:00', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(7, 'Frozen', '4D', 'S4', '1581980400000', '18:30', 100, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(8, 'Frozen', '2D', 'S1', '1582585200000', '17:00', 120, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(9, 'Rain Man', '2D', 'S2', '1582412400000', '20:00', 200, 'pera');

INSERT INTO projekcija (id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)
VALUES(10, 'Indiana Jones: Last Crusade', '4D', 'S4', '1581375600000', '21:00', 150, 'pera');



CREATE TABLE sala (
    id            INTEGER PRIMARY KEY,
    naziv         STRING  NOT NULL,
    tipProjekcije STRING  NOT NULL
);

INSERT INTO sala(id, naziv, tipProjekcije)
VALUES (1, 'S1', '2D');

INSERT INTO sala(id, naziv, tipProjekcije)
VALUES (2, 'S2', '2D, 3D');

INSERT INTO sala(id, naziv, tipProjekcije)
VALUES (3, 'S3', '3D');

INSERT INTO sala(id, naziv, tipProjekcije)
VALUES (4, 'S4', '4D');



CREATE TABLE sediste (
    id        INTEGER PRIMARY KEY,
    redniBroj INTEGER NOT NULL,
    sala      STRING  NOT NULL
);

INSERT INTO sediste (id, redniBroj, sala)
VALUES (1, 10, 'S1');

INSERT INTO sediste (id, redniBroj, sala)
VALUES (2, 12, 'S2');

INSERT INTO sediste (id, redniBroj, sala)
VALUES (3, 5, 'S3');

INSERT INTO sediste (id, redniBroj, sala)
VALUES (4, 1, 'S1');

INSERT INTO sediste (id, redniBroj, sala)
VALUES (5, 1, 'S2');



CREATE TABLE tipProjekcije (
    id    INTEGER PRIMARY KEY,
    naziv STRING  NOT NULL
);

INSERT INTO tipProjekcije(id, naziv)
VALUES (1, '2D');

INSERT INTO tipProjekcije(id, naziv)
VALUES (2, '3D');

INSERT INTO tipProjekcije(id, naziv)
VALUES (3, '4D');




CREATE TABLE izvestaj (
    id              INTEGER PRIMARY KEY,
    nazivFilma      STRING  NOT NULL,
    brojProjekcija  INTEGER NOT NULL,
    brojKarata      INTEGER NOT NULL,
    ukupnaCenaKarata DOUBLE  NOT NULL
);


INSERT INTO izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata)
VALUES (1, 'Mr. Bean', 3, 3, 3000);

INSERT INTO izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata)
VALUES (2, 'Sam u kuci', 2, 1, 100);

INSERT INTO izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata)
VALUES (3, 'Frozen', 2, 4, 400);

INSERT INTO izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata)
VALUES (4, 'Indiana Jones: Last Crusade', 1, 2, 300);

INSERT INTO izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata)
VALUES (5, 'Rain Man', 1, 10, 2000);
