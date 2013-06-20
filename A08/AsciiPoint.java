/**
 * Diese Klasse repräsentiert einen Punkt, spezifiziert durch zwei ganzzahlige Koordinaten.
 *
 * Diese Klasse ist unveränderlich (immutable), sprich die Koordinaten können nachträglich nicht mehr veränderbar werden. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class AsciiPoint {

	final private int x;
	final private int y;

	/**
	 * Erzeugt einen Punkt mit den angegebenen Koordinaten.
	 */
	public AsciiPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gibt die x-Koordinate des Punktes zurück.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die x-Koordinate des Punktes zurück.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gibt eine lesbare Darstellung des Punktes in der Form (x,y) zurück.
	 *
	 * @return Koordinaten des Punks in der Form "(x,y)"
	 */
	public String toString() {
		return "("+x+","+y+")";
	}
}

