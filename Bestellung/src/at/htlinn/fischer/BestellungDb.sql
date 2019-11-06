DROP TABLE IF EXISTS Bestellung;
CREATE TABLE Bestellung(
  id int(11) NOT NULL,
  kunde_id int(11) NOT NULL,
  adresse_rechnung_id int(11) NOT NULL,
  adresse_liefer_id int(11) NOT NULL,

  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Bestellung_Artikel;
CREATE TABLE Bestellung_Artikel(
  bestell_id int(11) NOT NULL,
  artikel_id int(11) NOT NULL,
  menge int(11) NOT NULL,

  FOREIGN KEY (bestell_id) REFERENCES Bestellung(id),
  PRIMARY KEY (bestell_id),
  PRIMARY KEY (artikel_id)

);

DROP TABLE IF EXISTS Artikel;
CREATE TABLE Artikel(
  id int(11) NOT NULL,
  name VARCHAR(110) NOT NULL,
  preis double NOT NULL,

  FOREIGN KEY (id) REFERENCES Bestellung_Artikel(artikel_id),
  PRIMARY KEY (id)

);

DROP TABLE IF EXISTS Adresse;
CREATE TABLE Adresse(
  id int(11) NOT NULL,
  stadt VARCHAR(110) NOT NULL,
  strasse VARCHAR(110) NOT NULL,
  plz int(11) NOT NULL,
  hnr VARCHAR(10) NOT NULL,

  FOREIGN KEY (id) REFERENCES Bestellung(adresse_liefer_id),
  FOREIGN KEY (id) REFERENCES Bestellung(adresse_rechnung_id),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Kunde;
CREATE TABLE Kunde(
  id int(11) NOT NULL,
  titelv VARCHAR(110) NOT NULL,
  vorname VARCHAR(110) NOT NULL,
  nachname VARCHAR(110) NOT NULL,
  titeln VARCHAR(110)

  FOREIGN KEY (id) REFERENCES Bestellung(id),
  PRIMARY KEY (id)


);

