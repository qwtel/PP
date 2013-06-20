/**
 * Ein BlockGenerator liefert die Helligkeitwerte eines n*n großen Bereichs in einem AsciiImage. Wird zum Erstellen einer {@link FilterOperation} benötigt.
 *
 * Diese Klasse implementiert die grundlegende Logik. 
 * Die Randbehandlung muss in einer abgeleiteten Klasse implementiert werden.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
abstract class BlockGenerator {

	private int n;

	/**
	 * Erzeugt einen Blockgenerator für einen n*n Block. 
	 *
	 * @param n Die Größe des Filters. Muss eine ungerade Zahlen größer 1 sein.
	 */
	public BlockGenerator(int n) {
		this.n = n;
	}

	/**
	 * Erzeugt eine Kopie des n*n großen Bereichs um die angegebene x/y Koordinate im AsciiImage img.
	 *
	 * @param img Das AsciiImage 
	 * @param x Die x-Koordinate im Bild.
	 * @param y Die y-Koordinate im Bild.
	 * @return Ein Block der Größe n*n mit den Helligkeitwerten der umliegenden Pixel.
	 */
	public int[] getBlock(AsciiImage img, int x, int y) {
		
		int[] block = new int[n*n];
		int i = 0;

		for(int ny = -(n/2); ny <= n/2; ny++) {
			for(int nx = -(n/2); nx <= n/2; nx++) {
				AsciiPoint p = new AsciiPoint(x+nx, y+ny);

				if(AsciiImage.isInBound(p,img)) {
					block[i++] = img.getCharset().indexOf(img.getPixel(p));
				}
				else {
					block[i++] = getOutOfBoundsValue(img, x+nx, y+ny);
				}
			}
		}
		return block;
	}

	/**
	 * Diese Methode entscheidet welche Werte für jene Pixel im Block gewählt werden, welche außerhalb des Bildbereichs liegen.
	 *
	 * @param img Das AsciiImage 
	 * @param x Die x-Koordinate außerhalb des Bilds.
	 * @param y Die y-Koordinate außerhalb des Bilds.
	 * @return Der Helligkeitswert für die angegebene Position.
	 */
	public abstract int getOutOfBoundsValue(AsciiImage img, int x, int y);
}
