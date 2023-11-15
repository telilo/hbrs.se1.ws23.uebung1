package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung2.*;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

public class Client {

    public void displayMemberview(Container container, MemberView memberView) {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);
        Member m4 = new ConcreteMember(4);
        Member m5 = new ConcreteMember(5);
        try {
            container.addMember(m1);
            container.addMember(m2);
            container.addMember(m3);
            container.addMember(m4);
            container.addMember(m5);
        } catch(Exception e) {
            e.printStackTrace();
        }
        memberView.dump(container.getCurrentList());
    }
}
