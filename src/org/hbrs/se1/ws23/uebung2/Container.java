package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> container = new ArrayList<Member>();

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

    public void dump() {
        for(Member counter: container) {
            System.out.println(counter.toString());
        }
    }

    public int size() {
        return this.container.size();
    }
}
