package org.hbrs.se1.ws23.uebung2;

public class ConcreteMember implements Member {
    private final int id;
    public ConcreteMember(int id) {
        this.id = id;
    }
    @Override
    public Integer getID() {
        return this.id;
    }
}
