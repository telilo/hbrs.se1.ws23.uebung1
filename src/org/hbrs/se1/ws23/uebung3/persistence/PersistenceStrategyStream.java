package org.hbrs.se1.ws23.uebung3.persistence;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.hbrs.se1.ws23.uebung2.*;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean connected = false;
    private ByteArrayOutputStream byteArrayOutputStream;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save.
     */
    public void openConnection() throws PersistenceException {
        if(this.connected == false) {
            try {
                File file = new File(this.location);
                if(file.exists() && this.location.endsWith("/")) {
                    file.createNewFile();
                    ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
                    o.writeObject(new ArrayList<>());
                    o.close();
                }
                this.fileInputStream = new FileInputStream(this.location);
                this.byteArrayOutputStream = new ByteArrayOutputStream();
                this.objectInputStream = new ObjectInputStream(this.fileInputStream);
                this.objectOutputStream = new ObjectOutputStream(this.byteArrayOutputStream);
            } catch (IOException io) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, io.getMessage());
            }
            this.connected = true;
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        if(this.connected == true) {
            try {
                this.objectInputStream.close();
                this.objectOutputStream.flush();
                this.objectOutputStream.close();
                this.fileInputStream.close();
                this.byteArrayOutputStream.flush();
                this.byteArrayOutputStream.close();
            } catch(IOException io) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, io.getMessage());
            }
            this.connected = false;
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        if(this.connected == true) {
            try {
                this.objectOutputStream.writeObject(member);
                this.objectOutputStream.flush();
                FileOutputStream fileOutputStream = new FileOutputStream(this.location);
                fileOutputStream.write(this.byteArrayOutputStream.toByteArray());
                fileOutputStream.close();
            } catch(IOException io) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, io.getMessage());
            }
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Member> load() throws PersistenceException  {
        // Some Coding hints ;-)
        if(this.connected == true) {
            try {
                List<Member> neueListe = (List<Member>) this.objectInputStream.readObject();
                return neueListe;
            } catch(IOException | ClassNotFoundException io) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, io.getMessage());
            }
        }
        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)
        return new ArrayList<>();
    }
}
