/**
 * Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerfälle, die beim Ausführen von Operationen auftreten, eingesetzt.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class OperationException extends Exception {

	/**
	 * Erzeugt eine leere OperationException.
	 *
	 * Ruft den entsprechenden Super-Konstruktor in der Klasse <code>Exception</code> auf. 
	 */
	public OperationException() {
		super();
	}

	/**
	 * Erzeugt eine OperationException mit der entsprechenden Fehlerbeschreibung.
	 *
	 * Ruft den entsprechenden Super-Konstruktor in der Klasse <code>Exception</code> auf. 
	 */
	public OperationException(String message) {
		super(message);
	}
}
