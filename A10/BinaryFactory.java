import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt BinaryOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class BinaryFactory implements Factory {
	
	/**
	 * Erzeugt eine neue BinaryOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte BinaryOperation.
	 * @throws FactoryException 
	 */
	public Operation create(Scanner scanner) throws FactoryException {
		if(scanner.findInLine("(\\S)") == null) {
			throw new FactoryException("Parameter fehlt");
		}

		MatchResult match = scanner.match();
		char c = match.group(1).charAt(0);

		return new BinaryOperation(c);
	}
}

