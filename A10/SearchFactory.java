import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt SearchOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class SearchFactory implements Factory {

	private MetricSet<AsciiImage> saved;

	/**
	 * Erzeugt eine neue SearchFactory.
	 *
	 * @param saved Eine Referenz auf ein MetricSet in dem die SearchOperation suchen soll.
	 */
	public SearchFactory(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}
	
	/**
	 * Erzeugt eine neue SearchOperation und gibt diese zurück. 
	 *
	 * @param sc Der Eingabestream aus dem die Paramter gelesen werden.
	 * @return Die erzeugte SearchOperation.
	 * @throws FactoryException Wenn Parameter fehlen.
	 */
	public Operation create(Scanner sc) throws FactoryException {
		if(sc.findInLine("(\\w+)") == null) {
			throw new FactoryException("Paramter fehlt");
		}

		MatchResult match = sc.match();
		String p = match.group(1).toLowerCase();

		Metric<AsciiImage> metric = null;
		if(p.equals("pixelcount")) {
			metric = new PixelCountMetric();
		}
		else if(p.equals("uniquechars")) {
			metric = new UniqueCharsMetric();
		}
		else {
			throw new FactoryException("Paramter ungültig");
		}

		return new SearchOperation(saved,metric);
	}
}
