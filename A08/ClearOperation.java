/**
 * Diese Klasse setzt alle Pixel des Bildes auf das hellste Zeichen. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class ClearOperation implements Operation {

	/**
	 * Erzeugt eine neue Operation. 
	 */
	public ClearOperation() {}

	/** 
	 * Gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, wobei alle Zeichen auf das hellste Zeichen gesetzt sind.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 */
	public AsciiImage execute(AsciiImage img) {
		AsciiImage result = new AsciiImage(img);
		clear(result);
		return result;
	}
		
	/**
	 * Setzt alle Pixel des Bildes auf das hellste Zeichen. Diese Methode verändert das übergebe Bild!
	 *
	 * @param result Das Bild das gecleared werden soll.
	 */
	public static void clear(AsciiImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		char bg = img.getBackgroundColor();

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				img.setPixel(x,y,bg);
			}
		}
	}
}
