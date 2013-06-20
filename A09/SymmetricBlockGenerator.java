/**
 * Bei diesem BlockGenerator wird für die Randbehandlung das Bild entlang der Kanten gespiegelt.  
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class SymmetricBlockGenerator extends BlockGenerator {

	/**
	 * Erzeugt einen Blockgenerator für einen n*n Block. 
	 *
	 * @param n Die Größe des Filters. Muss eine ungerade Zahlen größer 1 sein.
	 */
	public SymmetricBlockGenerator(int n) {
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

		int width = img.getWidth();
		int xAbs = Math.abs(x);
		int xLoops = xAbs / width;
		int xNew = 0;

		if(x >= width) {
			xNew = xAbs % width;	
			if(xLoops%2 == 1) {
				xNew = (width-1)-xNew;
			}
		}
		else if(x < 0) {
			xNew = (width-1) - (xAbs % width);
			if(xLoops%2 == 0) {
				xNew = (width-1)-xNew;
			}
		}

		int height = img.getHeight();
		int yAbs = Math.abs(y);
		int yLoops = yAbs / height;
		int yNew = 0;

		if(y >= height) {
			yNew = yAbs % height;	
			if(yLoops%2 == 1) {
				yNew = (height-1)-yNew;
			}
		}
		else if(y < 0) {
			yNew = (height-1) - (yAbs % height);
			if(yLoops%2 == 0) {
				yNew = (height-1)-yNew;
			}
		}

		return img.getCharset().indexOf(img.getPixel(xNew,yNew));
	}
}
