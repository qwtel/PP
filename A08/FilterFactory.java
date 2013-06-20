import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt Operations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class FilterFactory implements Factory {
	
	/**
	 * Erzeugt eine neue Operation und gibt diese zurück. 
	 *
	 * @return Die erzeugte Operation.
	 * @throws FactoryException wenn der Filtertyp fehlt oder unbekannt ist.
	 */
	public Operation create(Scanner scanner) throws FactoryException {
		if(scanner.findInLine("(\\w+)") == null) {
			throw new FactoryException("Filtertyp fehlt");
		}

		MatchResult match = scanner.match();
		String type = match.group(1);

		if(type.equals("median")) {
			return new MedianOperation();
		}
		else {
			throw new FactoryException("Unbekannter Filtertyp");
		}
	}
}

