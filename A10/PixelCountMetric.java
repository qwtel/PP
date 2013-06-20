/**
 * Eine einfache Metrik für Bilder, die Bildgrößen vergleicht. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class PixelCountMetric implements Metric<AsciiImage> {

	/**
	 * Liefert den Absolutbetrag der Differenz der Bildgrößen von i1 und i2.
	 *
	 * @param i1 Das erste AsciiImage.
	 * @param i2 Das zweite AsciiImage.
	 * @return Die Differenz der Bildgrößen.
	 */
	public int distance(AsciiImage i1, AsciiImage i2) {
		int size1 = i1.getWidth() * i1.getHeight();
		int size2 = i2.getWidth() * i2.getHeight();
		return Math.abs(size1 - size2);
	}
}
