package org.hbrs.se1.ws23.uebung3.persistence;

import java.util.List;
import org.hbrs.se1.ws23.uebung2.*;

public class PersistenceStrategyMongoDB<Member> implements PersistenceStrategy<Member> {

    @Override
    public void openConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void closeConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void save(List<Member> member) {
        throw new UnsupportedOperationException("Not implemented!");

    }

    @Override
    public List<Member> load() {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
