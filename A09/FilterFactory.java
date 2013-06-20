import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt FilterOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 2
 */
class FilterFactory implements Factory {
	
	/**
	 * Erzeugt eine neue FilterOperation und gibt diese zurück.
	 *
	 * @param scanner Enthält die Parameter für das erzeugen des Filters.
	 * @return Die erzeugte FilterOperation.
	 * @throws FactoryException wenn der Filtertyp fehlt oder unbekannt ist oder die Parameter ungültig sind.
	 */
	public Operation create(Scanner scanner) throws FactoryException {
		/*
		 * Ermittle Filtertyp
		 */
		if(scanner.findInLine("(\\w+)") == null) {
			throw new FactoryException("Filtertyp fehlt");
		}

		MatchResult match = scanner.match();
		String type = match.group();
		type = type.toLowerCase();
			
		/*
		 * Wurde eine Größe für das Filter spezifiziert? Sonst 3.
		 */
		int size = 3;
		if(scanner.findInLine("(\\d+)") != null) {
			match = scanner.match();
			size = Integer.parseInt(match.group());

			if(size%2 != 1) {
				throw new FactoryException("Filtergröße muss ungerade Zahl größer 1 sein");
			}
		}

		/*
		 * Wurde ein Modus für die Randbehandlung spezifiziert? Sonst "x"
		 */
		String mode = "x";
		if(scanner.findInLine("(\\w+)") != null) {
			match = scanner.match();
			mode = match.group();
			mode = mode.toLowerCase();
		}

		/*
		 * Erzeugt einen Blockgenerator für die FilterOperation.
		 */
		BlockGenerator block = null;
		if(mode.equals("x")) {
			block = new XBlockGenerator(size);	
		}
		else if(mode.equals("circular")) {
			block = new CircularBlockGenerator(size);	
		}
		else if(mode.equals("replicate")) {
			block = new ReplicateBlockGenerator(size);	
		}
		else if(mode.equals("symmetric")) {
			block = new SymmetricBlockGenerator(size);	
		}
		else {
			throw new FactoryException("Unbekannte Randbehandlung");
		}

		/*
		 * Erzeugt die eigentliche FilterOperation.
		 */
		FilterOperation filter = null;
		if(type.equals("median")) {
			filter = new MedianOperation(block);
		}
		else if(type.equals("average")) {
			filter = new AverageOperation(block);
		}
		else {
			throw new FactoryException("Unbekannter Filtertyp");
		}

		return filter;
	}
}

