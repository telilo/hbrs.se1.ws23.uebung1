package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container c1;

    @BeforeEach
    void setUp() {
        c1 = new Container();
    }
    @Test
    void testAddAndDeleteMember() {
        Member m1 = new ConcreteMember(12);
        Member m2 = new ConcreteMember(32);
        assertEquals(0, c1.size());

        try {
            c1.addMember(m1);
        } catch (ContainerException c) {
            c.printStackTrace();
        }
        assertEquals(1, c1.size());

        try {
            c1.addMember(m2);
        } catch (ContainerException c) {
            c.printStackTrace();
        }
        assertEquals(2, c1.size());

        assertThrows(ContainerException.class, () -> {
            c1.addMember(m2);
        });
        assertEquals(2, c1.size());
        assertThrows(ContainerException.class, () -> {
            c1.addMember(null);
        });
        assertEquals(2, c1.size());

        String ergebnis = c1.deleteMember(99);
        assertEquals(2, c1.size());
        assertEquals("Member nicht im Container enthalten", ergebnis);
        assertEquals(2, c1.size());

        //c1.dump();
        assertEquals(2, c1.size());

        c1.deleteMember(12);
        assertEquals(1, c1.size());
        c1.deleteMember(32);
        assertEquals(0, c1.size());
    }
    @Test
    void testAufNull() {
        try {
            c1.addMember(null);
        } catch(ContainerException c) {
            assertEquals("Der Member ist Null", c.getMessage());
        }
    }
}