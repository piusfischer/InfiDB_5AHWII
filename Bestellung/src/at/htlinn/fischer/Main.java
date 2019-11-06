package at.htlinn.fischer;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws SQLException {

        while(true){

            Scanner scanner = new Scanner(System.in);
            int number;
            do
            {
                System.out.println();
                System.out.println("Sie befinden sich nun im Hauptmenü!");
                System.out.println("Um eine Änderung in den Daten zumachen, entscheiden Sie sich bitte für eine Kategorie");
                System.out.println();
                System.out.println("1 für Kunden");
                System.out.println("2 für Adressen");
                System.out.println("3 für Artikel");
                System.out.println("4 für Bestellungen");
                System.out.println("5 für Beenden");
                System.out.println();
                System.out.println("Sie müssen nun eine der 5 Nummern drücken.");
                System.out.println();
                System.out.print("Kategorie: ");
                number = scanner.nextInt();

                System.out.flush();
            }while(number < 1 || number > 6);

            switch(number)
            {
                case 1: MainFunctions.Auswahl("Kunden"); break;
                case 2: MainFunctions.Auswahl("Adressen"); break;
                case 3: MainFunctions.Auswahl("Artikel"); break;
                case 4: MainFunctions.bestellungMenue(); break;
                default: scanner.close();System.exit(0);
            }

        }


    }
}
