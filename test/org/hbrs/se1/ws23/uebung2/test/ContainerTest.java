package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    Container c1;
    LinkedList<Member> l1;
    Container c2;
    LinkedList<Member> l2;

    @BeforeEach
    void setUp() {
        l1 = new LinkedList<>();
        l2 = new LinkedList<>();
        c1 = new Container(l1);
        c2 = new Container(l2);
    }
    @Test
    void testAddMember() throws ContainerException {
        Member m1 = new ConcreteMember(7);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(6);
        Member m4 = new ConcreteMember(23);
        Member m5 = new ConcreteMember(8);
        Member m6 = new ConcreteMember(7);
        c1.addMember(m1);
        assertEquals(m1.getID(), l1.getLast().getID());
        c1.addMember(m2);
        assertEquals(m2.getID(), l1.getLast().getID());
        c1.addMember(m3);
        assertEquals(m3.getID(), l1.getLast().getID());
        c1.addMember(m4);
        assertEquals(m4.getID(), l1.getLast().getID());
        c1.addMember(m5);
        assertEquals(m5.getID(), l1.getLast().getID());
        c2.addMember(m1);
        assertEquals(m1.getID(), l2.getLast().getID());
        c2.addMember(m2);
        assertEquals(m2.getID(), l2.getLast().getID());
        c2.addMember(m3);
        assertEquals(m3.getID(), l2.getLast().getID());
        //c1.addMember(m6);
        //assertEquals(m6.getID() + " ist bereits vorhanden!", l1.getFirst().getID());
    }
    @Test
    void testDeleteMember() throws ContainerException {
        Member m1 = new ConcreteMember(7);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(6);
        Member m4 = new ConcreteMember(23);
        Member m5 = new ConcreteMember(8);
        Member m6 = new ConcreteMember(7);
        c1.addMember(m1);
        c1.addMember(m2);
        c1.addMember(m3);
        c1.addMember(m4);
        c1.addMember(m5);
        assertEquals("Das Member-Objekt mit der ID " + 7 + " wurde entfernt", c1.deleteMember(7));
        assertEquals("Das Member-Objekt mit der ID " + 8 + " wurde entfernt", c1.deleteMember(8));
        assertEquals("Das Member-Objekt mit der ID " + 23 + " wurde entfernt", c1.deleteMember(23));
        assertEquals("Das Member-Objekt mit der ID " + 6 + " wurde entfernt", c1.deleteMember(6));
        assertEquals("Das Member-Objekt mit der ID " + 11 + " wurde entfernt", c1.deleteMember(11));
        //assertEquals("Es ist kein Element in der Liste enthalten!", c1.deleteMember(7));
        //assertEquals("Das Member-Objekt mit der ID " + 12 + " existiert nicht", c1.deleteMember(12));
    }
    @Test
    void testDump() throws ContainerException {
        Member m1 = new ConcreteMember(7);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(6);
        Member m4 = new ConcreteMember(23);
        Member m5 = new ConcreteMember(8);
        c1.addMember(m1);
        c1.addMember(m2);
        c1.addMember(m3);
        c1.addMember(m4);
        c1.addMember(m5);
        c1.dump();
    }
    @Test
    void testSize() throws ContainerException {
        Member m1 = new ConcreteMember(7);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(6);
        Member m4 = new ConcreteMember(23);
        Member m5 = new ConcreteMember(8);
        c1.addMember(m1);
        assertEquals(1, c1.size());
        c1.addMember(m2);
        assertEquals(2, c1.size());
        c1.addMember(m3);
        assertEquals(3, c1.size());
        c1.addMember(m4);
        assertEquals(4, c1.size());
        c1.addMember(m5);
        assertEquals(5, c1.size());
        c1.deleteMember(6);
        assertEquals(4, c1.size());
        assertEquals(0, c2.size());
    }
}
