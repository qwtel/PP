/**
 * Eine einfache Metrik für Bilder, die die Anzahl unterschiedlicher Zeichen vergleicht. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class UniqueCharsMetric implements Metric<AsciiImage> {

	/**
	 * Liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild.
	 *
	 * @param i1 Das erste AsciiImage.
	 * @param i2 Das zweite AsciiImage.
	 * @return Die Differenz der Anzahl unterschiedlicher Zeichen im Bild.
	 */
	public int distance(AsciiImage i1, AsciiImage i2) {
		int chars1 = i1.getUniqueChars();
		int chars2 = i2.getUniqueChars();
		return Math.abs(chars1 - chars2);
	}
}
