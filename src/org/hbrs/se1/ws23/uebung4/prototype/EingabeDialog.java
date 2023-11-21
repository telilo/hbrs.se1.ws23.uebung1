package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class EingabeDialog {
    public void startEingabe() {
        String strInput = null;
        System.out.println("UserStory-Tool V1.0 by Abel K.");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                strInput = input.readLine();
            } catch (IOException ioe) {
                // TODO Auto-generated catch block
                ioe.printStackTrace();
            }
            String[] strings = strInput.split(" ");
            // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if (strings[0].equals("help")) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump....");
            }
            if (strings[0].equals("dump")) {
                this.startAusgabe();
            }
            if (strings[0].equals("load")) {
                Container.getInstance().load();
            }
            if (strings[0].equals("store")) {
                try {
                    Container.getInstance().store();
                } catch (ContainerException c) {
                    c.printStackTrace();
                }
            }
            if (strings[0].equals("enter")) {
                MyConsole console = new MyConsole();
                // Ausgabe einer Eingabeaufforderung
                System.out.println("Geben sie die Grunddaten der User Story ein:");

                // Eingabe der ID:
                int id = console.readLineInt("ID der User Story: ");

                // Abfrage der einzelnen Werte der User Story:
                String titel = console.readLine("Titel der User Story: ");

                // Eingabe des Aufwands:
                int aufwand = console.readLineInt("Aufwand der User Story: ");

                // Eingabe des Risikos:
                int risk = console.readLineInt("Risiko der User Story: ");

                // Eingabe des Mehrwerts:
                int mehrwert = console.readLineInt("Mehrwert der User Story: ");

                // Eingabe der Strafe:
                int strafe = console.readLineInt("Strafe der User Story: ");

                // Berechnung der Priorisierung
                double prio =  ( (mehrwert + strafe ) / ( aufwand + risk ) );
                System.out.println("User Story mit ID: " + id + " hat die Prio: " + prio);

                // Neues Objekt vom Typ UserStory
                UserStory us = new UserStory( id, titel, mehrwert, strafe, aufwand, risk , prio );

                // Hinzufügen
                try {
                    Container.getInstance().addUserStory( us );
                } catch (ContainerException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Fehler beim Abspeichern der User Story!");
                }
            }
        }
    }

    private void startAusgabe() {
        AusgabeDialog dialog = new AusgabeDialog( Container.getInstance().getCurrentList() );
        dialog.dump();
    }
}
