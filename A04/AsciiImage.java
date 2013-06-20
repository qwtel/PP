import java.util.Stack;

/**
 * Ein Ascii-Bild mit Methoden zur Verwaltung des Bildes.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class AsciiImage {
	/**
	 * Das Ascii-Bild wird als Zeichenkette ohne Zeilenumbrüche gespeichert.
	 */
	private String image;

	private int width;

	private int height;

	/**
	 * Erzeugt ein leeres Bild. Die Breite des Bildes wird mit dem ersten Aufruf von addLine festgelegt. 
	 */
	public AsciiImage() {
		image = new String();
		width = 0;
		height = 0;
	}

	/** 
	 * Fügt eine Zeile am Ende des Bildes hinzu wenn die Länge der Zeile mit der Breite des Bildes übereinstimmt. 
	 *
	 * Beim ersten Aufruf der Methode wird die Breite des Bildes festgelegt.
	 *
	 * @param line Zeile die zum Bild hinzugefügt werden soll.
	 * @return true wenn die Breite eingehalten wurde, ansonsten false
	 */
	public boolean addLine(String line) {
		if(width == 0) {
			width = line.length();
		}
		
		if(line.length() == width) {
			image += line;
			height++;
			return true;
		}

		return false;
	}

	/**
	 * Liefert die Anzahl der Zeichen in einer Zeile des Bildes.
	 *
	 * @return Breite des Bildes.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Liefert die Anzahl der Zeichen in einer Spalte des Bildes.
	 *
	 * @return Höhe des Bildes.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Liefert das Ascii-Bild mit Zeilenumbrüchen am Ende jeder Zeile.
	 * 
	 * @return String des Bildes mit Zeilenumbrüchen.
	 *
	 */
	public String toString() {
		String output = image.substring( indexOf(0,0), indexOf(width,0) );

		for(int y=1; y<height; y++) {
			output += '\n' + image.substring( indexOf(0,y), indexOf(width,y) );
		}

		return output;
	}

	/**
	 * Liefert die Anzahl der unterschiedlichen Zeichen im Bild.
	 *
	 * Druchläuft das Bild zeichenweise und speichert alle unterschiedlichen Zeichen in einen String.
	 *
	 * @return Anzahl der unterschiedlichen Zeichen im Bild.
	 */
	public int getUniqueChars() {
		String uniqueChars = new String();
		char c;
		
		for(int i=0; i<image.length(); i++) {
			c = image.charAt(i);
			if(uniqueChars.contains(""+c) == false) {
				uniqueChars += c;
			}
		}

		return uniqueChars.length();
	}

	/**
	 * Spiegelt das Bild in vertikaler Richtung. 
	 *
	 * Durchläuft das Bild zeilenweise, beginnend mit der letzten Zeile und schreibt die gelesenen Zeilen in einen neuen String.
	 * Die letzte Zeile wird dabei zur Ersten, die Vorletzte zur Zweiten, usw.
	 */
	public void flipV() {
		String tmpImage = new String();

		for(int y=height-1; y>=0; y--) {
			tmpImage += image.substring( indexOf(0,y), indexOf(width,y) );
		}

		image = tmpImage;
	}

	/**
	 * Vertauscht Zeilen und Spalten des Bildes.
	 *
	 * Das Bild wird von "oben nach unten" und nicht wie üblich von "links nach rechts" durchlaufen und zeichenweise in einen neuen String geschieben.
	 * Dabei wird die erste Zeile zur ersten Spalte, usw.
	 *
	 */
	public void transpose() {
		String tmpImage = new String();
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tmpImage += getPixel(x,y);
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

	/**
	 * Liefert den Index einer X/Y-Koordinate im Bild.
	 *
	 * @param x Position in der Spalte beginnend bei 0.
	 * @param y Position in der Reihe beginnend bei 0.
	 * @return Der Index der übergebenen Koordinate in image.
	 */
	private int indexOf(int x, int y) {
		return y*width+x;
	}

	/** 
	 * Liefert das Zeichen das sich an einer X/Y-Koordinate im Bild befindet.
	 *
	 * @param x Position in der Spalte beginnend bei 0.
	 * @param y Position in der Reihe beginnend bei 0.
	 * @return Das Zeichen an der übergebenen Koordinate. 
	 */
	private char getPixel(int x, int y) {
		return image.charAt(indexOf(x,y));
	}

	/** 
	 * Setzt ein bestimmtes Zeichen an einer X/Y-Koordinate im Bild.
	 *
	 * @param x Position in der Spalte beginnend bei 0.
	 * @param y Position in der Reihe beginnend bei 0.
	 * @param c Das zu setzende Zeichen.
	 */
	private void setPixel(int x, int y, char c) {
		int pos = indexOf(x,y);
		image = image.substring(0,pos) + c + image.substring(pos+1);
	}
}

