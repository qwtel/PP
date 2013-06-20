/**
 * Erzeugt eine neue CreateOperation, die ein neues Bild mit angegebener Bildgöße und Zeichensatz erzeugt. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class CreateOperation implements Operation {

	private int width;
	private int height;
	private String charset;
	
	/**
	 * Erzeugt eine neue CreateOperation. 
	 *
	 * @param width Breite für das zu erzeugende Bild.
	 * @param height Höhe für das zu erzeugende Bild.
	 * @param charset Zeichensatz für das zu erzeugende Bild.
	 */
	public CreateOperation(int width, int height, String charset) {
		this.width = width;
		this.height = height;
		this.charset = charset;
	}

	/** 
	 * Gibt ein neues AsciiImage zurück. Der Parameter wird ignoriert (beispielsweise kann auch null übergeben werden). 
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException wenn sich die Koordinate nicht im Bild befindet oder das Zeichen sich nicht im Zeichensatz des Bildes befindet.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {
		return new AsciiImage(width,height,charset);
	}
}
