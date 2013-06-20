import java.util.Iterator;

/**
 * Sucht AsciiImages in einem MetricSet duch ein Metric.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class SearchOperation implements Operation {

	private MetricSet<AsciiImage> saved;
	private Metric<AsciiImage> m;

	/**
	 * Erzeugt eine neue SearchOperation. 
	 *
	 * @param saved Eine Referenz auf ein MetricSet, in dem die Bilder gesucht werden sollen.
	 * @param m Ein Metric das beim Suchen angewendet werden soll.
	 */
	public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
		this.saved = saved;
		this.m = m;
	}

	/** 
	 * Liefert ein Bild mit minimaler Distanz zum spezifizierten Bild und liefert dieses als Kopie zurück.
	 *
	 * @param img Das Bild das im MetricSet gespeichert werden soll.
	 * @return Eines (!) der Bilder, welches img hinsichtlich der Metric am ähnlichsten ist.
	 * @throws OperationException Wenn das MetricSet leer ist.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		if(saved.size() == 0) {
			throw new OperationException("MetricSet ist leer");
		}

		MetricSet<AsciiImage> searchResults = saved.search(img,m);
		Iterator<AsciiImage> iter = searchResults.iterator();
		AsciiImage image = new AsciiImage(iter.next());

		return image;
	}
}
