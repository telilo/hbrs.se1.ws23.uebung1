package org.hbrs.se1.ws23.uebung3.persistence;
import java.util.*;

import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

public class Container {
    private static Container instance;
    private List<Member> container = new ArrayList<Member>();
    private PersistenceStrategy<Member> persistenceStrategy;

    private Container() {}

    public static Container getInstance() {
        if(instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void addMember(Member member) throws ContainerException {
        if(member == null) {
            throw new ContainerException("Der Member ist Null");
        }
        if(contains(member)) {
            throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        this.container.add(member);
    }

    private boolean contains(Member neu) {
        Integer idNeu = neu.getID();
        for(Member counter: this.container) {
            if(idNeu.intValue() == counter.getID().intValue()) {
                return true;
            }
        }
        return false;
    }

    public String deleteMember(Integer id) {
        Member neu = getMember(id);
        if(neu == null) {
            return "Member nicht im Container enthalten";
        }
        this.container.remove(neu);
        return "Das Member-Objekt mit der ID " + neu.getID() + " wurde gelöscht!";
    }

    private Member getMember(Integer id){
        for(Member counter: this.container) {
            if(id == counter.getID().intValue()) {
                return counter;
            }
        }
        return null;
    }

    public List<Member> getCurrentList() {
        return this.container;
    }

    public PersistenceStrategy<Member> getStrategy() {
        return this.persistenceStrategy;
    }

    public void setStrategy(PersistenceStrategy<Member> strategie) {
        if(this.persistenceStrategy != null) {
            try {
                this.persistenceStrategy.closeConnection();
            } catch (PersistenceException p) {
                System.out.println("Strategie konnte nicht geschlossen werden");
            }
        }
        this.persistenceStrategy = strategie;
        try {
            this.persistenceStrategy.openConnection();
        } catch(PersistenceException p) {
            System.out.println("Strategie konnte nicht geöffnet werden");
        }
    }

    public int size() {
        return this.container.size();
    }

    public void store() throws PersistenceException {
        if(this.persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie vorhanden");
        }
        this.persistenceStrategy.openConnection();
        this.persistenceStrategy.save(this.container);
        this.persistenceStrategy.closeConnection();
    }

    public void load() throws PersistenceException {
        if(this.persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie vorhanden");
        }
        this.persistenceStrategy.openConnection();
        this.container = this.persistenceStrategy.load();
        this.persistenceStrategy.closeConnection();
    }
}
