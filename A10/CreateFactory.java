import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt CreateOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class CreateFactory implements Factory {
	
	/**
	 * Erzeugt eine neue CreateOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte Operation.
	 * @throws FactoryException Wenn Parameter fehlen oder ungültig sind.
	 */
	public Operation create(Scanner sc) throws FactoryException {
		if(sc.findInLine("(\\d+) (\\d+) (\\S+)") == null) {
			throw new FactoryException("Paramter fehlt");
		}

		MatchResult match = sc.match();
		int width = Integer.parseInt(match.group(1));
		int height = Integer.parseInt(match.group(2));
		String charset = match.group(3);

		if(width <= 0 || height <= 0 || charset.length() != AsciiImage.getUniqueChars(charset)) {
			throw new FactoryException("Ungültige Paramter");
		}

		return new CreateOperation(width,height,charset);	
	}
}
