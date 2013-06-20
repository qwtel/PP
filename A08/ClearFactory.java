import java.util.Scanner;

/**
 * Diese Factory erzeugt ClearOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class ClearFactory implements Factory {
	
	/**
	 * Erzeugt eine neue ClearOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte ClearOperation.
	 */
	public Operation create(Scanner scanner) {
		return new ClearOperation();
	}
}

