import java.util.Stack;
import java.util.ArrayList;

/**
 * Diese Klasse repräsentiert ein ASCII-Bild, es speichert die Zeichen des Bildes und bietet entsprechende Methoden zur Modifikation und zur Abfrage von Eigenschaften.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 4
 */
class AsciiImage {
	/**
	 * Das Ascii-Bild als 2D char-Array	 
	 */
	private char[][] image; 
	private int width;
	private int height;
	private String charset;

	/**
	 * Erzeugt ein ASCII-Bild der spezifizierten Größe und mit dem angegebenen Zeichensatz.
	 *
	 * Anfangs sind alle Pixel auf den hellsten Wert des Zeichensatzes (also dem letzten Zeichen des Strings) gesetzt.
	 *
	 * @param width Weite des Bildes
	 * @param height Höhe der Bildes
	 * @param charset Zeichensatz des Bildes
	 * @throws IllegalArgumentException falls der Zeichensatz leer ist oder Zeichen doppelt enthält oder Breite/Höhe des Bildes kleiner 0 sind.
	 *
	 */
	public AsciiImage(int width, int height, String charset) {
		if(width <= 0 || height <= 0 || charset.length() == 0 || charset.length() != getUniqueChars(charset)) {
			throw new IllegalArgumentException();
		}

		image = new char[height][width];
		this.width = width;
		this.height = height;
		this.charset = charset;

		ClearOperation.clear(this);
	}

	/**
	 * Ein Kopierkonstruktor. Erzeugt ein neues AsciiImage mit dem gleichen Inhalt, wie das übergebene Bild.
	 * 
	 * @param img Das zu kopierende AsciiImage
	 * @throws IllegalArgumentException falls der Zeichensatz leer ist oder Zeichen doppelt enthält oder Breite/Höhe des Bildes kleiner 0 sind.
	 */
	public AsciiImage(AsciiImage img) {
		this(img.width, img.height, img.charset);

		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				setPixel(x,y,img.getPixel(x,y));
			}
		}
	}

	/**
	 * Gibt den Zeichensatz des Bildes als String zurück. 
	 *
	 * @return Zeichensatz des Bildes
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * Liefert die Hintergrundfarbe des Bildes.
	 *
	 * @return Das hellste Zeichen im Zeichensatz des Bildes.
	 */
	public char getBackgroundColor() {
		return charset.charAt(charset.length()-1);
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
	 * @throws IndexOutOfBoundsException wenn die Indizes außerhalb des Bildes liegen.
	 */ 
	public char getPixel(int x, int y) {
		return image[y][x];
	}

	/**
	 * Gibt, analog zur Methode {@link #getPixel(int x, int y)}, das Zeichen an der durch p spezifizierten Stelle zurück. 
	 *
	 * @param p Koordinate als AsciiPoint
	 * @return Das Zeichen an der Koordinate
	 * @throws IndexOutOfBoundsException wenn die Indizes außerhalb des Bildes liegen.
	 */ 
	public char getPixel(AsciiPoint p) {
		return getPixel(p.getX(), p.getY());
	}

	/**
	 * Gibt eine ArrayList aller Pixel eines bestimmten Zeichens zurück.
	 *
	 * In dieser ArrayList sind Objekte vom Typ AsciiPoint. 
	 * Sollte es keine Punkte mit dem angegebenen Zeichen geben, so soll eine leere Liste zurückgegeben werden.
	 * Verwenden Sie diese Methode überall dort, wo sie alle Pixel mit einem bestimmten Zeichen benötigen. 
	 *
	 * @param c Das Zeichen dessen Vorkommnisse im Bild gesucht wird
	 * @return Liste aller Pixel des Zeichens c als AsciiPoint
	 */ 
	public ArrayList<AsciiPoint> getPointList(char c) {
		ArrayList<AsciiPoint> pointList = new ArrayList<AsciiPoint>();

		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(c == getPixel(x,y)) {
					pointList.add(new AsciiPoint(x,y));
				}
			}
		}

		return pointList;
	}

	/**
	 * Gibt die Breite des Bildes (die Anzahl der Spalten) zurück.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Speichert an den übergebenen Koordinaten/Indizes das übergebene Zeichen.  
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param c Zeichen das gesetzt werden solll
	 * @throws IndexOutOfBoundsException wenn die Indizes außerhalb des Bildes liegen oder das Zeichen nicht dem Zeichensatz des Bilds entspricht 
	 */
	public void setPixel(int x, int y, char c) {
		image[y][x] = c;
	}

	/**
	 * Speichert, analog zur Methode {@link #setPixel(int x, int y, char c)}, das übergebene Zeichen an der durch den p spezifizierten Stelle. 
	 *
	 * @param p Stelle im Bild als AsciiPoint
	 * @param c Zeichen das gesetzt werden solll
	 * @throws IndexOutOfBoundsException wenn die Indizes außerhalb des Bildes liegen oder das Zeichen nicht dem Zeichensatz des Bilds entspricht 
	 */
	public void setPixel(AsciiPoint p, char c) {
		setPixel(p.getX(), p.getY(), c);
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
	 * Entscheided ob sich ein Punkt in einem Bild befindet.
	 *
	 * @param p Punkt als AsciiPoint
	 * @param img Bild als AsciiImage
	 * @return true wenn es sich der Punkt im Bild befindet
	 */
	public static boolean isInBound(AsciiPoint p, AsciiImage img) {
		int x = p.getX();
		int y = p.getY();
		int width = img.getWidth();
		int height = img.getHeight();
		return (x >= 0 && y >= 0 && x < width && y < height);
	}

	/**
	 * Liefert die unterschiedlichen Zeichen im übergebenen String.
	 *
	 * @param s Der zu untersuchende String.
	 * @return Die unterschiedlichen Zeichen im String.
	 */
	public static String getUniqueCharsAsString(String s) {
		String uniqueChars = new String();
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);

			if(uniqueChars.indexOf(c) <= -1) {
				uniqueChars += c;
			}
		}

		return uniqueChars;
	}

	/**
	 * Liefert die Anzahl der unterschiedlichen Zeichen im übergebenen String.
	 *
	 * @param s Der zu untersuchende String.
	 * @return Anzahl der unterschiedlichen Zeichen im String.
	 */
	public static int getUniqueChars(String s) {
		return getUniqueCharsAsString(s).length();
	}
}
