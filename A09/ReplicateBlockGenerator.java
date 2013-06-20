/**
 * Bei diesem BlockGenerator werden die am Rand befindlichen Pixel über den Bildrand hinweg fortgesetzt.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class ReplicateBlockGenerator extends BlockGenerator {

	/**
	 * Erzeugt einen Blockgenerator für einen n*n Block. 
	 *
	 * @param n Die Größe des Filters. Muss eine ungerade Zahlen größer 1 sein.
	 */
	public ReplicateBlockGenerator(int n) {
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
		int height = img.getHeight();
		
		int nx = x;
		int ny = y;

		if(x >= width) {
			nx = width-1;	
		}
		else if(x < 0) {
			nx = 0;
		}

		if(y >= height) {
			ny = height-1;
		}
		else if(y < 0) {
			ny = 0;
		}
		
		return img.getCharset().indexOf(img.getPixel(nx,ny));
	}
}
