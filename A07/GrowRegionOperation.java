import java.util.ArrayList;

/**
 * Vergrößert die Flächen aller Pixel des Zeichens c, in dem es an diese Pixel angrenzende Hintergrundpixel auf das Zeichen c setzt. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class GrowRegionOperation implements Operation {
	
	private char c;

	/**
	 * Erzeugt eine neue GrowRegionOperation. 
	 * 
	 * @param c Das Zeichen dessen Flächen vergrößert werden sollen
	 */
	public GrowRegionOperation(char c) {
		this.c = c;
	}

	/** 
	 * Führt die Operation auf eine Kopie des übergebenen Bildes aus und gibt dieses zurück.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException wenn sich das Zeichen nicht im Zeichensatz des Bildes befindet.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		if(!img.getCharset().contains(""+c)) {
			throw new OperationException("Zeichen nicht im Zeichensatz");
		}

		AsciiImage result = new AsciiImage(img);
		ArrayList<AsciiPoint> pointList = img.getPointList(img.getBackgroundColor());

		for(AsciiPoint point : pointList) {
			int x = point.getX();
			int y = point.getY();

			if((y-1)>=0 && img.getPixel(x,y-1) == c) {
				result.setPixel(x,y,c);
			}
			if((x-1)>=0 && img.getPixel(x-1,y) == c) {
				result.setPixel(x,y,c);
			}
			if((y+1)<img.getHeight() && img.getPixel(x,y+1) == c) {
				result.setPixel(x,y,c);
			}
			if((x+1)<img.getWidth() && img.getPixel(x+1,y) == c) {
				result.setPixel(x,y,c);
			}
		}

		return result;
	}

}

