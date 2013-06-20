/**
 * Bei diesem BlockGenerator wird das Bild mehrfach nebeneinander gelegt, direkt an die letzte Spalte schließt die erste Spalte an und analog für die Zeilen. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class CircularBlockGenerator extends BlockGenerator {

	/**
	 * Erzeugt einen BlockGenerator für einen n*n Block. 
	 *
	 * @param n Die Größe des Filters. Muss eine ungerade Zahlen größer 1 sein.
	 */
	public CircularBlockGenerator(int n) {
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
		
		int nx = Math.abs(x);
		int ny = Math.abs(y);

		nx = nx%width;	
		if(x < 0) {
			nx = width-nx;
		}

		ny = ny%height;
		if(y < 0) {
			ny = height-ny;
		}

		return img.getCharset().indexOf(img.getPixel(nx,ny));
	}
}
