package org.hbrs.se1.ws23.uebung2;
import java.util.LinkedList;
public class Container {
    public LinkedList<Member> container = new LinkedList<>();
    private int size;
    public Container(LinkedList<Member> container) {
        this.container = container;
        this.size = 0;
    }
    public void addMember(Member member) throws ContainerException {
        for(int i = 0; i < this.size; i++) {
            if(this.container.get(i).getID().equals(member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        this.container.add(this.size++, member);
    }
    public String deleteMember(Integer id) throws ContainerException {
        if(this.size == 0) {
            throw new ContainerException("Es ist kein Element in der Liste enthalten!");
        }
        for(int i = 0; i < this.size; i++) {
            if(this.container.get(i).getID().equals(id)) {
                String ergebnis = "Das Member-Objekt mit der ID " + id + " wurde entfernt";
                this.container.remove(i);
                this.size--;
                return ergebnis;
            }
        }
        throw new ContainerException("Das Member-Objekt mit der ID " + id + " existiert nicht");
    }
    public void dump() {
        for(int i = 0; i < this.size; i++) {
            System.out.println("Member (ID = " + this.container.get(i).getID().toString() + ")");
        }
    }
    public int size() {
        return this.size;
    }
}
