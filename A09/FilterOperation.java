import java.util.Arrays;

/**
 * Diese Klasse beinhaltet die Funktionalität, um das Bild zu durchlaufen und für jeden Pixel den benötigten Block an Nachbarpixeln zu bestimmen. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public abstract class FilterOperation implements Operation {

	private BlockGenerator blockGenerator;

	/**
	 * Konstruktor der FilterOperation
	 *
	 * @param block Ein BlockGenerator welcher die Größe des Filter und die Randbehandlung spezifiziert.
	 */
	public FilterOperation(BlockGenerator blockGenerator) { 
		this.blockGenerator = blockGenerator;
	}

	/**
	 * Führt den Blockfilter aus. 
	 *
	 * Diese Methode muss von abgeleiteten Klassen nicht überschrieben werden. 
	 * Die Methode durchläuft das Bild, bestimmt für jeden Pixel den Block an Nachbarpixeln und ruft damit dann die Methode filter auf. 
	 * Das Ergebnis dieser Methode wird dann als neuer Pixel an der aktuellen Stelle gesetzt. 
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 */
	public AsciiImage execute(AsciiImage img) {
		AsciiImage result = new AsciiImage(img);
		int width = img.getWidth();
		int height = img.getHeight();	

		String charset = img.getCharset();

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				int[] block = blockGenerator.getBlock(img,x,y);
				int value = filter(block);
				result.setPixel(x,y,charset.charAt(value));
			}
		}

		return result;
	}

	/**
	 * Führt die eigentliche Logik des Filters durch. Muss von den abgeleiteten Klassen implementiert werden. 
	 *
	 * @param values Umfasst die Helligkeitswerte der Pixel im Block Zeile für Zeile.
	 * @return Der Berechnete Helligkeitswert.
	 */
	public abstract int filter(int[] values); 
}
