package at.htlinn.fischer;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainFunctions {
    public static void Auswahl(String type)
    {
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            int number;
            do
            {
                System.out.println();
                System.out.println(type+" Erweitertemenü");
                System.out.println();
                System.out.println("Um eine Änderung in den Daten zumachen, entscheiden Sie sich bitte für eine Kategorie");
                System.out.println();
                System.out.println("1 für "+type+" erstellen");
                System.out.println("2 für "+type+" anzeigen");
                System.out.println("3 für "+type+" loeschen");
                System.out.println("4 wenn Sie zurueck ins Hauptmenü wollen.");
                System.out.println();
                System.out.println();
                System.out.print("Aktivität: ");
                number = scanner.nextInt();
                System.out.flush();
            }while(number < 1 || number > 5);


            if(number == 1)
            {
                if(type.equals("Kunden")) {
                    kundenErstellen();
                }
                else if(type.equals("Adressen")){
                    adressenErstellen();
                }
                else if(type.equals("Artikel")){
                    artikelErstellen();
                }
            }
            else if(number == 2)
            {
                anzeigen(type);
            }
            else if(number == 3)
            {
                loeschen(type);
            }
            else
            {
                return;
            }
        }
    }

    public static void loeschen(String type)
    {


        Scanner scanner = new Scanner(System.in);
        String number;

        do
        {
            System.out.println();
            System.out.println(type+" Loeschen aus der folgenden Liste");
            System.out.println();
            anzeigen(type);
            System.out.println();
            System.out.print("Um ein Objekt aus der Liste zu loeschen bitte geben Sie die dazugehörige ID jetzt an.");
            System.out.println("ID= ");
            number = scanner.next();
            System.out.flush();
        }while(!number.matches("[0-9]+"));

        try
        {
            DatabaseManager database=new DatabaseManager();
            if(type.equals("Kunden"))
            {
                database.deleteKunde(Integer.valueOf(number));
            }
            else if(type.equals("Adressen"))
            {
                database.deleteAdresse(Integer.valueOf(number));
            }
            else if(type.equals("Artikel"))
            {
                database.deleteArtikel(Integer.valueOf(number));
            }
            else if(type.equals("Bestellungen"))
            {
                database.deleteBestellung(Integer.valueOf(number));
            }

            database.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim loeschen aufgetreten!");
            e.printStackTrace();
        }
    }

    public static void anzeigen(String type)
    {
        try
        {
            DatabaseManager db=new DatabaseManager();
            if(type.equals("Kunden"))
            {
                db.getKunden();
            }
            else if(type.equals("Adressen"))
            {
                db.getAdressen();
            }
            else if(type.equals("Artikel"))
            {
                db.getArtikel();
            }
            else if(type.equals("Bestellungen"))
            {
                db.getBestellung();
            }
            else if(type.equals("Bestellung_Artikel"))
            {
                db.getArtikelBestellung();
            }
            db.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Anzeigen aufgetreten!");
            e.printStackTrace();
        }
    }


    public static void kundenErstellen()
    {
        String titelv=null;
        String vorname=null;
        String nachname=null;
        String titeln=null;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println();
            System.out.println("Erstellen eines Kunden");
            System.out.println();
            System.out.println("Hier können Sie einen neuen Kunden anlegen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();
            System.out.print("Vorname: "); vorname = scanner.next();
            System.out.print("Nachname: "); nachname = scanner.next();
            System.out.print("Titel vorne: "); titelv = scanner.next();
            System.out.print("Titel hinten: "); titeln = scanner.next();
            System.out.flush();
        }while(!isStringOnlyAlphabet(vorname) || !isStringOnlyAlphabet(nachname) || !isStringOnlyAlphabet(titelv) || !isStringOnlyAlphabet(titeln));

        try
        {
            DatabaseManager database=new DatabaseManager();
            database.setKunde(titelv, vorname, nachname, titeln);
            database.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Erstellen aufgetreten.");
            e.printStackTrace();
        }
    }


    public static void adressenErstellen()
    {
        String stadt=null;
        String strasse=null;
        String hnr=null; //Falls jemand ein Buchstaben in der Hausnummer hat wie zum Beispiel 1a, 1b
        int plz=0;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Erstellen einer Adresse");
            System.out.println();
            System.out.println("Hier können Sie eine neue Adresse anlegen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();
            System.out.print("Stadt: ");
            stadt = scanner.next();
            System.out.print("Strasse: ");
            strasse = scanner.next();
            System.out.print("Hausnummer: ");
            hnr = scanner.next();
            try
            {
                System.out.print("PLZ: "); plz = scanner.nextInt();
            }catch(InputMismatchException e)
            {
                System.out.println("Die PLZ muss eine Zahl sein. Bitte erneut probieren.");
                scanner.next();
                continue;
            }

            System.out.flush();
        }while(!isStringOnlyAlphabet(stadt) || !isStringOnlyAlphabet(strasse) || !isStringOnlyAlphabet(hnr) || plz==0);

        try
        {
            DatabaseManager db=new DatabaseManager();
            db.setAdresse(stadt, strasse, plz, hnr);
            db.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Erstellen aufgetreten.");
            e.printStackTrace();
        }
    }


    public static void artikelErstellen()
    {
        String name=null;
        double preis=0;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Erstellen eines Artikels");
            System.out.println();
            System.out.println("Hier können Sie einen neuen Artikel anlegen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();
            System.out.print("Name: "); name = scanner.next();
            System.out.print("Preis: "); preis = scanner.nextDouble();

            System.out.flush();
        }while(!isStringOnlyAlphabet(name));

        try
        {
            DatabaseManager database=new DatabaseManager();
            database.setArtikel(name, preis);
            database.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Erstellen aufgetreten.");
            e.printStackTrace();
        }
    }


    public static void bestellungMenue()
    {
        while(true)
        {

            Scanner scanner = new Scanner(System.in);
            int number;
            do
            {
                System.out.println();
                System.out.println(" Erweitertemenue Bestellung");
                System.out.println();
                System.out.println("Um diverse Aktivitäten zu machen bitte entscheiden Sie sich für eine Kategorie");
                System.out.println();
                System.out.println("1 für Bestellung erstellen");
                System.out.println("2 für Bestellungen anzeigen");
                System.out.println("3 für Bestellung l�schen");
                System.out.println();
                System.out.println("4 für Artikel zu Bestellung hinzuf�gen ");
                System.out.println("5 für Artikel einer Bestellung anzeigen ");
                System.out.println("6 für Artikel von Bestellung l�schen ");
                System.out.println();
                System.out.println("7 um zurueck ins ErweiterteMenue zu gelangen.");
                System.out.println();
                System.out.println("Um jetzt einer der Aktivitäten durchzuführen bitte drücken Sie eine der 7 Zahlen");
                System.out.println();
                System.out.print("Auswahl: ");
                number = scanner.nextInt();
                System.out.flush();
            }while(number < 1 || number > 7);


            if(number == 1) { bestellungErstellen();}
            else if(number == 2) {anzeigen("Bestellungen");}
            else if(number == 3) {loeschen("Bestellungen");}
            else if(number == 4) {artikeltoBestellung();}
            else if(number == 5) {anzeigen("Bestellung_Artikel");}
            else if(number == 6) {bestellungArtikelloeschen();}
            else {return;}
        }
    }


    public static void bestellungErstellen()
    {

        int kunde_id=0;
        int adresse_rechnung_id=0;
        int adresse_liefer_id;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Erstellen einer Bestellung");
            System.out.println();
            System.out.println("Hier haben Sie eine Uebersicht über die Daten der Adressen und des Kunden");
            System.out.println();
            System.out.println("Adressen: ");
            anzeigen("Adressen");
            System.out.println("\nKunden: ");
            anzeigen("Kunden");
            System.out.println();
            System.out.println();
            System.out.println("Hier können Sie einen neuen Artikel anlegen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();


            try
            {
                System.out.print("Kunden ID: ");
                kunde_id = scanner.nextInt();
                System.out.print("Liefer-Adresse ID: ");
                adresse_rechnung_id = scanner.nextInt();
                System.out.print("Liefer-Rechnung ID: ");
                adresse_liefer_id = scanner.nextInt();

            }catch(InputMismatchException e)
            {
                System.out.println("Es wurde ein Fehler bei der Eingabe gemacht, bitte versuchen Sie es nocheinmal.");
                scanner.next();
                continue;
            }
            System.out.flush();
            break;
        }while(true);

        try
        {
            DatabaseManager db=new DatabaseManager();
            db.setBestellung(kunde_id, adresse_rechnung_id, adresse_liefer_id);
            db.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Erstellen aufgetreten.");
            e.printStackTrace();
        }
    }


    public static void artikeltoBestellung()
    {

        int bestell_id=0;
        int artikel_id=0;
        int menge=0;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Artikel zur Bestellung hinzufuegen.");
            System.out.println();
            System.out.println("Hier haben Sie eine Uebersicht über die Daten der Bestellungen, Adressen, Kunden und Artikel");
            System.out.println();
            System.out.println("Bestellungen: ");
            anzeigen("Bestellungen");
            System.out.println("\n\nAdressen: ");
            anzeigen("Adressen");
            System.out.println("\n\nKunden: ");
            anzeigen("Kunden");
            System.out.println("\n\nArtikel: ");
            anzeigen("Artikel");
            System.out.println();
            System.out.println();
            System.out.println("Hier können Sie einen neuen Artikel zur Bestellung hinzufuegen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();

            try
            {
                System.out.print("Bestell ID: "); bestell_id = scanner.nextInt();
                System.out.print("Artikel ID: "); artikel_id = scanner.nextInt();
                System.out.print("Menge: "); menge = scanner.nextInt();
            }catch(InputMismatchException e)
            {
                System.out.println("Es ist ein Fehler beim Hinzufuegen aufgetreten.");
                scanner.next();
                continue;
            }
            System.out.flush();
            break;
        }while(true);

        try
        {
            DatabaseManager database=new DatabaseManager();
            database.addArtikeltoBestellung(bestell_id, artikel_id, menge);
            database.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Hinzufuegen aufgetreten.");
            e.printStackTrace();
        }
    }


    public static void bestellungArtikelloeschen()
    {

        int bestell_id=0;
        int artikel_id=0;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Bestellung eines Artikels loeschen.");
            System.out.println();
            System.out.println("Hier haben Sie eine Uebersicht über die Daten der Bestellte Artikel, Bestellungen und Artikel");
            System.out.println();
            System.out.println("Bestellte Artikel: ");
            anzeigen("Bestellung_Artikel");
            System.out.println("\n\nBestellungen: ");
            anzeigen("Bestellungen");
            System.out.println("\n\nArtikel: ");
            anzeigen("Artikel");
            System.out.println();
            System.out.println();
            System.out.println("Hier können Sie eine Bestellung eines Artikels loeschen, dafuer geben sie bitte folgende Daten ein.");
            System.out.println();

            try
            {
                System.out.print("Bestell ID: "); bestell_id = scanner.nextInt();
                System.out.print("Artikel ID: "); artikel_id = scanner.nextInt();
            }catch(InputMismatchException e)
            {
                System.out.println("Es ist ein Fehler beim Loeschen aufgetreten.");
                scanner.next();
                continue;
            }
            System.out.flush();
            break;
        }while(true);

        try
        {
            DatabaseManager database=new DatabaseManager();
            database.deleteArtikelfromBestellung(bestell_id, artikel_id);
            database.closeProgramm();

        } catch (SQLException e)
        {
            System.out.println("Es ist ein Fehler beim Hinzufuegen aufgetreten.");
            e.printStackTrace();
        }
    }




    public static boolean isStringOnlyAlphabet(String str)
    {
        return ((!str.equals("")) && (str != null) && (str.matches("^[a-zA-Z]*$")));
    }



}
