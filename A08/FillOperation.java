/**
 * Befüllt eine Fläche ausgehend von einer Kooridinate X/Y mit dem Zeichen c.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class FillOperation implements Operation {

	private int x;
	private int y;
	private char c;
	
	/**
	 * Erzeugt eine neue FillOperation. 
	 *
	 * @param x X-Koordinate eines Punktes im Bild.
	 * @param y Y-Kooridnate eines Punktes im Bild.
	 * @param c Das Zeichen das für die Füllung verwenden werden soll.
	 */
	public FillOperation(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}

	/** 
	 * Führt die Operation auf eine Kopie des übergebenen Bildes aus und gibt dieses zurück.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException wenn sich die Koordinate nicht im Bild befindet oder das Zeichen sich nicht im Zeichensatz des Bildes befindet.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		AsciiPoint p = new AsciiPoint(x,y);
		if(!AsciiImage.isInBound(p, img)) {
			throw new OperationException("Koordinaten nicht im Bild");
		}
		if(img.getCharset().indexOf(c) <= -1) {
			throw new OperationException("Zeichen nicht im Zeichensatz");
		}

		AsciiImage result = new AsciiImage(img);
		fill(result,x,y,c);
		return result;
	}

	/**
	 * Implementierung des Algorithmus zu Befüllung von Flächen in einem AsciiImage nach der Vierer-Nachbarschafts-Regel. (rekursiv)
	 *
	 * Diese Methode verändert das übergebene AsciiImage!
	 *
	 * @param img Das AsciiImage in dem eine Fläche Befüllt werden soll.
	 * @param x X-Koordinate eines Punktes im Bild.
	 * @param y Y-Kooridnate eines Punktes im Bild.
	 * @param c Das Zeichen das für die Füllung verwenden werden soll.
	 */
	public static void fill(AsciiImage img, int x, int y, char c) {
		char old = img.getPixel(x,y);
		img.setPixel(x,y,c);

		Debug.log(img);

		if((y-1)>=0 && img.getPixel(x,y-1) == old) {
			fill(img, x,   y-1, c);
		}
		if((x-1)>=0 && img.getPixel(x-1,y) == old) {
			fill(img, x-1, y  , c);
		}
		if((y+1)<img.getHeight() && img.getPixel(x,y+1) == old) {
			fill(img, x  , y+1, c);
		}
		if((x+1)<img.getWidth() && img.getPixel(x+1,y) == old) {
			fill(img, x+1, y  , c);
		}
	}
}
