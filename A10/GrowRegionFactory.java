import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt GrowRegionOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class GrowRegionFactory implements Factory {
	
	/**
	 * Erzeugt eine neue GrowRegionOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte Operation.
	 * @throws FactoryException wenn der Filtertyp fehlt oder unbekannt ist.
	 */
	public Operation create(Scanner sc) throws FactoryException {
		if(sc.findInLine("(\\S)") == null) {
			throw new FactoryException("Paramter fehlt");
		}

		MatchResult match = sc.match();
		char c = match.group(1).charAt(0);

		return new GrowRegionOperation(c);	
	}
}

