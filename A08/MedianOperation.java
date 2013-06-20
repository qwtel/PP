import java.util.Collections;
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
