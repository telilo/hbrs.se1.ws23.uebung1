package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
public class PersistenceManager {

    // URL der Datei, in der die Objekte gespeichert werden
    public final static String LOCATION = "allStories.ser";

    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    public static void store(List<UserStory> list) throws ContainerException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(PersistenceManager.LOCATION);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.out.println(list.size() + " User Stories wurden erfolgreich gespeichert!");
        }
        catch (IOException ioe) {
            // Koennte man ausgeben für interne Debugs: e.printStackTrace();
            // Chain of Responsibility: Hochtragen der Exception in Richtung Ausgabe (UI)
            // Uebergabe in ein lesbares Format fuer den Benutzer
            throw new ContainerException("Fehler beim Laden der Datei!");
        }
        finally {
            if (oos != null) try { oos.close(); } catch (IOException ioe) {}
            if (fos != null) try { fos.close(); } catch (IOException ioe) {}
        }
    }
    /*
     * Methode zum Laden der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte geladen.
     * Die geladene Liste überschreibt aktuell die vorhandenen Objekte
     * in der Liste --> Optimierung notwendig ;-!
     *
     */
    public static List<UserStory> load() {
        List<UserStory> list = null;
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( PersistenceManager.LOCATION );
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object o = ois.readObject();
            if (o instanceof List<?>) {
                list = (List) o;
            }
            System.out.println("Es wurden " + list.size() + " User Stories erfolgreich reingeladen!");
            return list;
        }
        catch (IOException ioe) {
            // Sup-Optimal, da Exeception in Form eines unlesbaren Stake-Traces ausgegeben wird
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c) {
            // Verbesserung, aber Chain of Responsbility nicht erfuellt, da UI nicht
            // benachrichtigt wird unter Umstaenden. Verbesserung: siehe Methoden store!
            System.out.println("FEHLER: Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try { ois.close(); } catch (IOException ioe) {}
            if (fis != null) try { fis.close(); } catch (IOException ioe) {}
        }
        return list;
    }
}