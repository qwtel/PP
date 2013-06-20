/**
 * Vertauscht Zeilen und Spalten des Bildes.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class TransposeOperation implements Operation {

	/**
	 * Erzeugt eine neue TransposeOperation
	 */
	public TransposeOperation() {}

	/**
	 * Gibt ein bearbeitetes AsciiImage zurück.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		int width = img.getHeight();
		int height = img.getWidth();
		AsciiImage result = new AsciiImage(width,height,img.getCharset());

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				result.setPixel(x,y,img.getPixel(y,x));
				Debug.log(result);
			}
		}

		return result;
	}
}
