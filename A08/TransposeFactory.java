import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt TransposeOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class TransposeFactory implements Factory {
	
	/**
	 * Erzeugt eine neue TransposeOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte Operation.
	 */
	public Operation create(Scanner sc) {
		return new TransposeOperation();	
	}
}

