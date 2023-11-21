package org.hbrs.se1.ws23.uebung4.prototype;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AusgabeDialog {
    List<UserStory> liste = null;

    public AusgabeDialog(List<UserStory> currentList) {
        this.liste = currentList;
    }

    public void dump(){
        if ( liste.isEmpty() ) {
            System.out.println("Keine User Stories vorhanden");
            return;
        }
        this.dumpLambdaWithFilterAndMapAndSort();
    }

    private void dumpIterator() {
        Collections.sort(  this.liste );
        Iterator<UserStory> i = liste.iterator();
        while (  i.hasNext() ) {
            UserStory p = i.next();
            System.out.println("ID: " + p.getId() );
            System.out.println("Titel: " + p.getTitel());
            System.out.println("Prio: " + p.getPrio());
            System.out.println("Aufwand: " + p.getAufwand());
            System.out.println("Risiko: " + p.getRisk());
            System.out.println("Mehrwert: " + p.getMehrwert());
            System.out.println("Risiko: " + p.getRisk());
            System.out.println("\n");
        }
        System.out.println("Aktuelle Anzahl User Stories: " + liste.size() );
    }

    private void dumpForEach() {
        for ( UserStory us : liste ) {
            System.out.println( us.toString() );
        }
    }

    private void dumpFunctionInterface() {
        liste.forEach(new Consumer<UserStory>() {
            @Override
            public void accept(UserStory us) {System.out.println(us.toString());
            }
        });
    }

    private void dumpLambdaInFunctionalInterface() {
        liste.forEach((UserStory us) -> {
            System.out.println(us.toString());
        });
        liste.forEach( us  -> System.out.println( us.toString() ) ); // Reduce
    }

    private void dumpLambdaInStream() {
        liste.stream().forEach( us  -> System.out.println( us.toString() ) ); // Reduce
    }

    private void dumpLambdaWithFilter() {
        liste.stream().filter( us -> us.getRisk() > 4)   // Filter
                .forEach( us  -> System.out.println( us.toString() ) ); // Reduce
    }

    private void dumpLambdaWithFilterAndMap() {
        liste.stream()
                .filter( element -> element.getAufwand() > 4 )
                .map( element -> element.getTitel() )
                .forEach( element -> System.out.println( element ) );
    }

    private void dumpLambdaWithFilterAndMapParallel() {
        liste.parallelStream()
                .filter(element -> element.getAufwand() > 4)
                .map(element -> element.getTitel())
                .forEach(element -> System.out.println(element));
    }

    private void dumpLambdaWithFilterAndMapAndSort() {
        liste.stream()
                .filter(element -> element.getAufwand() > 4)
                .sorted((i1, i2) -> Double.compare(i1.getPrio(), i2.getPrio()))
                .forEach( element -> System.out.println( element ) );
    }

    private List<UserStory> dumpLambdaWithFilterAndMapAndCollect() {
        return liste.stream()
                .filter(element -> element.getAufwand() > 4)
                .sorted((i1, i2) -> Double.compare(i1.getPrio(), i2.getPrio()))
                .collect(Collectors.toList());
    }
}
