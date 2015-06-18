# Example JPA Mapping inheritance

## Anforderungen

- Oracle JDK 1.8
- NetBeans IDE 8 (https://netbeans.org/)
- Derby DB (in NetBeans enthalten)
- Maven 3 (in NetBeans enthalten)


## Vorbereitung
- JRE installieren
- NetBeans installieren
- in NetBeans unter "Services" eine neue JavaDB Datenbank anlegen
  - Name: ExampleJpaMappingInheritance
  - User: example
  - Pass: example


## Beispiel Anforderung

- Es existieren 2 Ausprägungen eines Vertrages. Den Kartenvertrag und den PFMobilevertrag
- Es soll möglich sein alle Verträge zu suchen
- Es soll möglich sein nur Kartenverträge zu suchen
- Es soll möglich sein nur PFMobileverträge zu suchen


## Datenmodell

Tabelle "VERTRAG"
- INTEGER ID
- INTEGER VERTAGART

Tabelle "KARTENVERTRAG"
- INTEGER ID
- INTEGER KARTEID
- INTEGER KONTONR

Tabelle "PFMOBILEVERTRAG"
- INTEGER ID
- VARCHAR HANDYNR


## Objektmodell

abstract class Vertrag {
    Long id;
    Integer vertragart;
}

class KartenVertrag extends Vertrag {
    Integer karteId;
    Integer kontoNr;
}

class PFMobileVertrag extends Vertrag {
    Integer handyNr;
}