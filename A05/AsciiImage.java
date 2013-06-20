import java.util.Stack;

/**
 * Ein Ascii-Bild mit Methoden zur Verwaltung des Bildes.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 2
 */
class AsciiImage {
	/**
	 * Das Ascii-Bild als 2D char-Array	 
	 */
	private char[][] image;

	private int width;

	private int height;

	/**
	 * Erzeugt ein ASCII-Bild der spezifizierten Größe. Alle Pixel werden auf den Wert '.' gesetzt. 
	 *
	 * @param width Weite des Bilds
	 * @param height Höhe der Bilds
	 */
	public AsciiImage(int width, int height) {
		image = new char[height][width];
		this.width = width;
		this.height = height;
		clear();
	}

	/**
	 * Setzt alle Pixel des Bildes auf das Zeichen '.'. 
	 */
	public void clear() {
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				image[y][x] = '.';
			}
		}
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
	 * Zusätzlich müssen in Fall 3 und 4 X und Y auch auch beim Setzen des Punkts vertauscht werden: setPixel(y,x) statt (x,y)
	 * Durch das Zurückführen aller Fälle auf den 1. Fall wird redudanter Code vermieden.
	 *
	 * @param x0 X-Koordinate des Anfangspunkts
	 * @param y0 Y-Koordinate des Anfangspunkts
	 * @param x1 X-Koordinate des Endpunkts
	 * @param y1 Y-Koordinate des Endpuntks
	 * @param c Zeichen das für die Linie verwendet werden soll 
	 */
	public void drawLine(int x0, int y0, int x1, int y1, char c) {
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
				setPixel(x,(int)Math.round(y),c);
			}
			else {
				setPixel((int)Math.round(y),x,c);
			}

			y += ((double)deltaY)/((double)deltaX);
		}
	}

	/**
	 * Gibt die Höhe des Bildes (die Anzahl der Zeilen) zurück.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gibt das an den übergebenen Koordinaten/Indizes gespeicherte Zeichen zurück.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return Das Zeichen an der Koordinate
	 */ 
	public char getPixel(int x, int y) {
		return image[y][x];
	}

	/**
	 * Gibt die Breite des Bildes (die Anzahl der Spalten) zurück.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Ersetzt alle Vorkommen eines bestimmten Zeichens oldChar im Bild durch ein anderes Zeichen newChar. 
	 *
	 * @param oldChar Zu ersetzendes Zeichen 
	 * @param newChar Zeichen das gesetzt werden soll
	 */ 
	public void replace(char oldChar, char newChar) {
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(getPixel(x,y) == oldChar) {
					setPixel(x,y,newChar);
				}
			}
		}
	}

	/**
	 * Speichert an den übergebenen Koordinaten/Indizes das übergebene Zeichen.  
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param c Zeichen das gesetzt werden solll
	 */
	public void setPixel(int x, int y, char c) {
		image[y][x] = c;
	}

	/**
	 * Gibt eine lesbare Darstellung des ASCII-Bildes zurück. Die einzelnen Zeilen werden dabei durch Zeilenumbrüche '\n' getrennt. 
	 *
	 * @return Das Bild als String
	 */
	public String toString() {
		int y=0;
		String output = new String(image[y]);

		for(y=1; y<getHeight(); y++) {
			output += "\n" + new String(image[y]);
		}

		return output;
	}

	/**
	 * Vertauscht Zeilen und Spalten des Bildes.
	 *
	 * Das Bild wird durchlaufen und zeichenweise in einem neuen Char-Array mit vertauschten X/Y-Koordinaten gesetzt.
	 * Verändert Höhe und Breite des Bilds!
	 */
	public void transpose() {
		char[][] tmpImage = new char[width][height];
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				tmpImage[x][y] = getPixel(x,y);
			}
		}

		int swp = height;
		height = width;
		width = swp;

		image = tmpImage;
	}

	/**
	 * Befüllt eine Fläche ausgehend von einer Kooridinate X/Y mit dem Zeichen c nach der Vierer-Nachbarschafts-Regel.
	 *
	 * @param x Position in der Spalte beginnend bei 0.
	 * @param y Position in der Reihe beginnend bei 0.
	 * @param c Füllfarbe
	 */
	public void fill(int x, int y, char c) {
		char old = getPixel(x,y);
		setPixel(x,y,c);
		
		if((y-1)>=0 && getPixel(x,y-1) == old) {
			fill(x,y-1,c);
		}
		if((x-1)>=0 && getPixel(x-1,y) == old) {
			fill(x-1,y,c);
		}
		if((y+1)<height && getPixel(x,y+1) == old) {
			fill(x,y+1,c);
		}
		if((x+1)<width && getPixel(x+1,y) == old) {
			fill(x+1,y,c);
		}
	}

	/**
	 * Entscheided ob sich eine gegebene Koordinate X/Y im Bild befindet.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return true wenn es sich um eine gültige Koordinate handelt
	 */
	public boolean isInBound(int x, int y) {
		return (x >= 0 && y >= 0 && x < getWidth() && y < getHeight());
	}

	/**
	 * Liefert die Anzahl der unterschiedlichen Zeichen im Bild.
	 *
	 * @return Anzahl der unterschiedlichen Zeichen im Bild.
	 */
	public int getUniqueChars() {
		String uniqueChars = new String();
		
		for(int y=0; y<width; y++) {
			for(int x=0; x<width; x++) {
				char c = getPixel(x,y);

				if(uniqueChars.contains(""+c) == false) {
					uniqueChars += c;
				}
			}
		}

		return uniqueChars.length();
	}

	/**
	 * Spiegelt das Bild in vertikaler Richtung. 
	 *
	 * Durchläuft das Bild bis zur Hälfte und vertauscht dabei Zeilen.
	 * Die letzte Zeile wird zur Ersten, die Vorletzte zur Zweiten, usw.
	 */
	public void flipV() {
		for(int y=0; y<height/2; y++) {
			char[] swp = image[y];
			image[y] = image[height-1-y];
			image[height-1-y] = swp;
		}
	}
	/**
	 * Ermittelt ob das Bild entlang der horizontalen Achse symmetrisch ist.
	 *
	 * Zu beachten ist, dass auch true geliefert werden kann, wenn die Breite des Bildes nicht gerade ist: 
	 * In diesem Fall wird das Zeichen in der Mitte als die "Achse" um die gespiegelt wird interpretiert.
	 * z.B.: abmba - true
	 *
	 * @return true wenn alle Zeilen symmetrisch sind.
	 */
	public boolean isSymmetricH() {
		Stack<Character> stack = new Stack<Character>();
		int x, y;

		for(y=0; y<height; y++) {
			for(x=0; x<width/2; x++) {
				stack.push(getPixel(x,y));
			}

			assert(x == width/2);

			for(x+=(width%2); x<width; x++) {
				if(stack.pop() != getPixel(x,y)) {
					return false;
				}
			}
		}

		return true;
	}
}

