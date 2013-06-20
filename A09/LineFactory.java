import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt LineOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class LineFactory implements Factory {
	
	/**
	 * Erzeugt eine neue LineOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte BinaryOperation.
	 * @throws FactoryException 
	 */
	public Operation create(Scanner sc) throws FactoryException {
		if(sc.findInLine("(\\d+) (\\d+) (\\d+) (\\d+) (\\S)") == null) {
			throw new FactoryException("Parameter fehlt");
		}

		MatchResult match = sc.match();
		int x0 = Integer.parseInt(match.group(1));
		int y0 = Integer.parseInt(match.group(2));
		int x1 = Integer.parseInt(match.group(3));
		int y1 = Integer.parseInt(match.group(4));
		char c = match.group(5).charAt(0);
		
		return new LineOperation(x0,y0,x1,y1,c);
	}
}

