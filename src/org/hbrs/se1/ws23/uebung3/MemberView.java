package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung2.*;

import java.util.List;

// Dieses Aufgabenblatt ist in Teamarbeit von Abel Kabeto (akabet2s)
// und Muhammad Oso (moso2s) bearbeitet worden.

public class MemberView {
    public void dump(List<Member> container) {
        for(Member counter: container) {
            System.out.println(counter.toString());
        }
    }
}
