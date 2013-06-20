/**
 * Bei diesem Blockgenerator werden Pixel die außerhalb des Bilds liegen mit den Hintergrund-Pixeln aufgefüllt.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class XBlockGenerator extends BlockGenerator {

	/**
	 * Erzeugt einen Blockgenerator für einen n*n Block. 
	 *
	 * @param n Die Größe des Filters. Muss eine ungerade Zahlen größer 1 sein.
	 */
	public XBlockGenerator(int n) {
		super(n);
	}

	/**
	 * Diese Methode entscheidet welche Werte für jene Pixel im Block gewählt werden, welche außerhalb des Bildbereichs liegen.
	 *
	 * @param img Das AsciiImage 
	 * @param x Die x-Koordinate außerhalb des Bilds.
	 * @param y Die y-Koordinate außerhalb des Bilds.
	 * @return Der Helligkeitswert für die angegebene Position.
	 */
	public int getOutOfBoundsValue(AsciiImage img, int x, int y) {
		return img.getCharset().indexOf(img.getBackgroundColor());
	}
}
