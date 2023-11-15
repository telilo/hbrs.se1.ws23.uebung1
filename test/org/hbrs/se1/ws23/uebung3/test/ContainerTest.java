package org.hbrs.se1.ws23.uebung3.test;

import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung3.*;
import org.hbrs.se1.ws23.uebung3.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

public class ContainerTest {
    Container container;
    PersistenceStrategy<Member> mongoDB;
    PersistenceStrategy<Member> stream;
    Member m1;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        mongoDB = new PersistenceStrategyMongoDB<>();
        stream = new PersistenceStrategyStream<>();
        m1 = new ConcreteMember(1);
    }

    @Test
    void testSetStrategy() {
        assertEquals(null, container.getStrategy());
        assertThrows(PersistenceException.class, () -> {
            container.load();
        });
    }

    @Test
    void testMongoDB() {
        assertThrows(UnsupportedOperationException.class, () -> {
            mongoDB.openConnection();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            mongoDB.closeConnection();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            mongoDB.save(container.getCurrentList());
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            mongoDB.load();
        });
    }

    @Test
    void testRoundTrip() throws ContainerException, PersistenceException {
        container.addMember(m1);
        List<Member> neu = container.getCurrentList();
        assertEquals(neu, container.getCurrentList());
    }
}
