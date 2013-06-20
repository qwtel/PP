/**
 * Speichert AsciiImages in einem MetricSet. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class SaveOperation implements Operation {

	private MetricSet<AsciiImage> saved;

	/**
	 * Erzeugt eine neue SaveOperation. 
	 *
	 * @param saved Eine Referenz auf ein MetricSet, in dem die Bilder gespeichert werden sollen.
	 */
	public SaveOperation(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}

	/** 
	 * Fügt das spezifizierte Bild zum MetricSet hinzu.
	 *
	 * @param img Das Bild das im MetricSet gespeichert werden soll.
	 * @return Eine Kopie des spezifizierten Bilds.
	 * @throws OperationException Wenn  das Bild bereits im MetricSet vorhanden ist.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		if(saved.contains(img)) {
			throw new OperationException("Bild bereits gespeichert");
		}

		AsciiImage image = new AsciiImage(img);
		saved.add(img);
		return image;
	}
	
	/**
	 * Liefert die Collection mit gespeicherten Bildern.
	 *
	 * @return Alle gespeicherten Bilder als MetricSet.
	 */
	public MetricSet<AsciiImage> getSaved() {
		return this.saved;
	}
}
