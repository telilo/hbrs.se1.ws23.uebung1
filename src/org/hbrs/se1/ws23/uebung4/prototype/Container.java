package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.*;
import java.util.*;
public class Container {
    private List<UserStory> container = new ArrayList<>();
    private static Container instance;

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    private Container() {}

    public void addUserStory (UserStory u) throws ContainerException {
        if(u == null) {
            throw new ContainerException("Die UserStory ist Null");
        }
        if(contains(u)) {
            throw new ContainerException("Die UserStory mit der ID " + u.getId() + " ist bereits vorhanden!");
        }
        this.container.add(u);

    }

    private boolean contains(UserStory u) {
        int ID = u.getId();
        for (UserStory counter: this.container) {
            if (counter.getId() == ID) {
                return true;
            }
        }
        return false;
    }

    public int size(){
        return this.container.size();
    }

    public List<UserStory> getCurrentList() {
        return this.container;
    }

    public List<UserStoryTitle> getCurrentListOfUserStoriesAsTitle() {
        List<UserStoryTitle> newContainer = new ArrayList<>();
        for(UserStory counter : this.container ) {
            UserStoryTitle titel = new UserStoryTitle();
            titel.setTitel(counter.getTitel());
            newContainer.add(titel);
        }
        return newContainer;
    }

    private UserStory getUserStory(int id) {
        for (UserStory counter : this.container) {
            if (id == counter.getId()){
                return counter;
            }
        }
        return null;
    }

    public void load() {
        this.container = PersistenceManager.load();
    }

    public void store() throws ContainerException {
        PersistenceManager.store( this.container );
    }
}
