import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt FillOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class FillFactory implements Factory {
	
	/**
	 * Erzeugt eine neue FillOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte Operation.
	 * @throws FactoryException Wenn Parameter fehlen.
	 */
	public Operation create(Scanner sc) throws FactoryException {
		if(sc.findInLine("(\\d+) (\\d+) (\\S)") == null) {
			throw new FactoryException("Paramter fehlt");
		}

		MatchResult match = sc.match();
		int x = Integer.parseInt(match.group(1));
		int y = Integer.parseInt(match.group(2));
		char c = match.group(3).charAt(0);

		return new FillOperation(x,y,c);	
	}
}

