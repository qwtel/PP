import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

/**
 * Diese Klasse glättet ein Bild mit einem 3x3-Medianfilter.
 * 
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class MedianOperation implements Operation {
	
	/**
	 * Erzeugt eine neue MedianOperation
	 */
	public MedianOperation() {}

	/**
	 * Führt auf einer Kopie des Bildes den Medianfilter aus.
	 *
	 * Dabei werden immer 3 mal 3 Größe Blöcke des Bildes betrachtet, die Pixel nach ihrem `Helligkeitswert' sortiert und dann der Median (also das in der sortierten Liste in der Mitte stehende Zeichen) als neues Pixel im Mittelpunkt des Blocks gesetzt.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 */
	public AsciiImage execute(AsciiImage img) {
		AsciiImage result = new AsciiImage(img);
		int width = img.getWidth();
		int height = img.getHeight();	
		char bg = img.getBackgroundColor();
		BrightnessComparator brightness = new BrightnessComparator(img.getCharset());

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {

				ArrayList<Character> neighbours = new ArrayList<Character>(9);

				for(int ny=-1; ny<=1; ny++) {
					for(int nx=-1; nx<=1; nx++) {
						AsciiPoint p = new AsciiPoint(x+nx, y+ny);

						if(!AsciiImage.isInBound(p,img)) {
							neighbours.add(bg);
						}
						else {
							neighbours.add(img.getPixel(p));
						}
					}
				}

				Collections.sort(neighbours,brightness);
				char median = neighbours.get(9/2); 
				result.setPixel(x,y,median);

				Debug.log(neighbours);
			}
		}

		return result;
	}

}
/**
 * Vergleicht zwei Character nach ihrer Helligkeit. 
 *
 * Diese ist durch ihre Ordnung in einem String definiert. Das hellste Zeichen befindet sich am Ende des Strings, das Dunkelste am Anfang.
 * Liefert unerwartete Ergebnisse wenn sich die chars für den Vergleich nicht im String befinden oder der String leer ist!
 */
class BrightnessComparator implements Comparator<Character> {
	
	private String charset;

	/**
	 * Erzeugt einen neuen Comparator durch einen String der die Ordnung der chars definiert.
	 *
	 * @param charset Ein nicht-leerer String der die Ordnung repräsentiert, beginnend mit dem Dunkelsten.
	 */
	public BrightnessComparator(String charset) {
		this.charset = charset;
	}

	/** 
	 * Führt einen Vergleich nach Helligkeit durch.
	 * 
	 * @param c1 Erstes Zeichen für den Vergleich. Muss in charset enthalten sein!
	 * @param c2 Zweites Zeichen für den Vergleich. Muss in charset enthalten sein!
	 * @return -1 wenn das erste Zeichen heller ist, 0 wenn die Zeichen gleich sind, +1 wenn das zweite Zeichen heller ist.
	 */
	public int compare(Character c1, Character c2) {
		return (int)Math.signum((double)charset.indexOf(c1) - (double)charset.indexOf(c2));
	}
}
