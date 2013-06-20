import java.util.Scanner;

/**
 * Definiert eine Schnittstelle über die eine Operation bezogen werden kann.
 *
 * Eine Facotry liest bei Befarf Parameter ein und generiert daraus eine neue <code>Operation</code>.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
interface Factory {

	/**
	 * Erzeugt ein neues Objekt vom Typ {@link Operation}. 
	 *
	 * Welche konkrete Operation erzeugt wird, hängt von der implementierenden Factory ab.
	 * Bei Bedarf liest diese Methode vom übergebenen Scanner Parameter ein. 
	 *
	 * @param scanner Beeinhaltet Paramter falls diese zur Erzeugung der Operation nötig sind.
	 * @return Die Operation der jeweiligen Factory.
	 * @throws FactoryException Wenn Parameter fehlen oder vom falschen Typ sind.
	 */
	public Operation create(Scanner scanner) throws FactoryException;
}
