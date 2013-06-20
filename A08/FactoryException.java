/**
 * Diese Klasse erweitert <code>Exception</code> und wird zum Behandeln aller Fehlerfälle, die in einer <code>Factory</code>, also beim Einlesen von Parametern oder dem Erzeugen eines Befehls, auftreten, eingesetzt.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class FactoryException extends Exception {

	/**
	 * Erzeugt eine leere FactoryException.
	 *
	 * Ruft den entsprechenden Super-Konstruktor in der Klasse <code>Exception</code> auf. 
	 */
	public FactoryException() {
		super();
	}

	/**
	 * Erzeugt eine FactoryException mit der entsprechenden Fehlerbeschreibung.
	 *
	 * Ruft den entsprechenden Super-Konstruktor in der Klasse <code>Exception</code> auf. 
	 */
	public FactoryException(String message) {
		super(message);
	}
}

