import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt LoadOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class LoadFactory implements Factory {
	
	/**
	 * Erzeugt eine neue LoadOperation und gibt diese zurück. 
	 *
	 * @return Die erzeugte LoadOperation.
	 * @throws FactoryException Wenn Parameter fehlen.
	 */
	public Operation create(Scanner scanner) throws FactoryException {
		if(scanner.findInLine("(\\S+)") == null) {
			throw new FactoryException("eof Paramter fehlt");
		}
		
		MatchResult match = scanner.match();
		String eof = match.group(1);

		scanner.nextLine();
		String line = scanner.nextLine();
		String lines = line;

		while(scanner.hasNext()) {
			line = scanner.next();

			if(line.equals(eof)) {
				break;
			}

			lines += "\n" + line;
		}

		if(!line.equals(eof)) {
			throw new FactoryException("Keine Ende in Sicht...");
		}
		
		return new LoadOperation(lines);
	}
}
