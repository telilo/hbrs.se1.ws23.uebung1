package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.hbrs.se1.ws23.uebung1.control.TranslatorFactory;

public class Client {

	private Translator translator;
	public Client(Translator translator) {
		this.translator = translator;
	}
	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!

			 // Ein GermanTranslator-Objekt, dass das Translator-Interface implementiert
			 Translator translator = TranslatorFactory.createGermanTranslator();
			 String result = translator.translateNumber(aNumber);

			System.out.println("Das Ergebnis der Berechnung: " +
					"[das Ergebnis an dieser Stelle]" + result);

		 }
}





