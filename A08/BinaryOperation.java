/**
 * Diese Klasse wandelt ein AsciiImage in ein Binärbild um. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class BinaryOperation implements Operation {

	private char threshold; 

	/**
	 * Erzeugt eine neue BinaryOperation mit dem entsprechenden Schwellwert.
	 *
	 * @param c Der Schwellenwert as char.
	 */
	public BinaryOperation(char c) {
		threshold = c;
	}

	/**
	 * Gibt ein neues AsciiImage zurück, das das Binärbild des übergebenen AsciiImage ist. 
	 *
	 * Zur Umwandlung in ein Binärbild werden alle Zeichen, die im Zeichensatz des Bildes vor dem Schwellwert kommen,
	 * auf das dunkelste Zeichen gesetzt, alle Zeichen ab dem Schwellwert werden auf das hellste Zeichen gesetzt. 
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException Wenn sich das Zeichen nicht im Zeichensatz des Bildes befindet.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		if(img.getCharset().indexOf(threshold) <= -1) {
			throw new OperationException("Zeichen nicht im Zeichensatz");
		}

		AsciiImage image = new AsciiImage(img);
		int width = img.getWidth();
		int height = img.getHeight();	
		char bg = img.getBackgroundColor();
		char fg = img.getCharset().charAt(0);
		BrightnessComparator brightness = new BrightnessComparator(img.getCharset());

		Debug.log(bg+"\n"+fg);

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(brightness.compare(img.getPixel(x,y),threshold) < 0) {
					image.setPixel(x,y,fg);
				}
				else {
					image.setPixel(x,y,bg);
				}
			}
		}

		return image;
	}
}

