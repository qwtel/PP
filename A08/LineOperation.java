/**
 * Zeichnet eine Linie im Bild. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class LineOperation implements Operation {

	private int x0;
	private int y0;
	private int x1;
	private int y1;
	private char c;

	/**
	 * Erzeugt eine neue Operation.
	 *
	 * @param x0 X-Koordinate des Anfangspunkts
	 * @param y0 Y-Koordinate des Anfangspunkts
	 * @param x1 X-Koordinate des Endpunkts
	 * @param y1 Y-Koordinate des Endpuntks
	 * @param c Zeichen das für die Linie verwendet werden soll 
	 */
	public LineOperation(int x0, int y0, int x1, int y1, char c) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.c = c;
	}

	/**
	 * Zeichnet eine Linie zwischen den Koordinaten (x0,y0) und (x1,y1). c spezifiziert das zu verwendende Zeichen. 
	 *
	 * Berechnung mithilfe des DDA-Algorithmus. 
	 * Unterscheidet 4 Fälle:
	 * <ol>
	 * <li>Endpunkt liegt "rechs" vom Anfangspunkt und Winkel zwischen -45 und +45 Grad. 
	 * <li>Endpunkt liegt "links" vom Anfangspunkt und Winkel zwischen -45 und +45 Grad. Wird durch Vertauschen von Anfangs und Endwert auf Fall 1 zurück geführt.
	 * <li>Endpunkt liegt "rechts" vom Anfangspunkt und Winkel größer +/- 45 Grad. Wird durch Vertauschen von X und Y auf Fall 1 zurück geführt.
	 * <li>Endpunkt liegt "links" vom Anfangspunkt und Winkel größer +/- 45 Grad. Wird durch Vertauschen von X und Y auf Fall 2 zurück geführt.
	 * </ol>
	 *
	 * Zusätzlich müssen in Fall 3 und 4 X und Y auch auch beim Setzen des Punkts vertauscht werden: setPixel (y,x) statt (x,y)
	 * Durch das Zurückführen aller Fälle auf den 1. Fall wird redudanter Code vermieden.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException wenn sich die Koordinaten nicht im Bild befinden oder das Zeichen sich nicht im Zeichensatz des Bildes befindet.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		AsciiPoint p0 = new AsciiPoint(x0,y0);
		AsciiPoint p1 = new AsciiPoint(x1,y1);
		if(!AsciiImage.isInBound(p0, img) || !AsciiImage.isInBound(p1, img)) {
			throw new OperationException("Koordinaten nicht im Bild");
		}
		if(img.getCharset().indexOf(c) <= -1) {
			throw new OperationException("Zeichen nicht im Zeichensatz");
		}

		AsciiImage result = new AsciiImage(img);
		int deltaX = x1 - x0;
		int deltaY = y1 - y0;
		boolean swapped = false;
		
		if(Math.abs(deltaY) > Math.abs(deltaX)) {
			int swp = x0;
			x0 = y0;
			y0 = swp;

			swp = x1;
			x1 = y1;
			y1 = swp;

			swp = deltaX;
			deltaX = deltaY;
			deltaY = swp;

			swapped = true;
		}

		if(x1 < x0) {
			int swp = x0;
			x0 = x1;
			x1 = swp;

			swp = y0;
			y0 = y1;
			y1 = swp;
		}

		int x = x0;
		double y = (double)y0;
		
		for(x=x0; x<=x1; x++) {
			if(!swapped) {
				result.setPixel(x,(int)Math.round(y),c);
			}
			else {
				result.setPixel((int)Math.round(y),x,c);
			}

			y += ((double)deltaY)/((double)deltaX);
		}

		return result;
	}
}
