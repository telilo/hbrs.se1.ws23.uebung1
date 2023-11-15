package org.hbrs.se1.ws23.uebung3;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

public class Main {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        container.setStrategy(new PersistenceStrategyStream<>());
        Client client = new Client();
        client.displayMemberview(Container.getInstance(), new MemberView());
    }
}
