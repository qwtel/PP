import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Diese Factory erzeugt SaveOperations. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class SaveFactory implements Factory {

	private MetricSet<AsciiImage> saved;

	/**
	 * Erzeugt eine neue SaveFactory.
	 *
	 * @param saved Eine Referenz auf ein MetricSet, dem durch eine SaveOperation Bilder hinzugefügt werden sollen.
	 */
	public SaveFactory(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}
	
	/**
	 * Erzeugt eine neue SaveOperation und gibt diese zurück. 
	 *
	 * @param scanner Wird ignoriert. Kann auch null sein. 
	 * @return Die erzeugte SaveOperation.
	 */
	public Operation create(Scanner scanner) throws FactoryException {
		return new SaveOperation(saved);
	}
}
