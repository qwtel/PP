import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.HashMap;
import java.util.Comparator;

/**
 * Simple Debug-Klasse.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class Debug {
	public static boolean debug = false;

	/** 
	 * Gibt Eine Debug-Nachricht auf die Konsole aus wenn {@link #debug} true ist.
	 */
	public static void log(Object message) {
		if(debug) {
			System.out.println("Debug-Information:----------------------\n" + message +"\n----------------------------------------");
		}
	}
}

/**
 * AsciiShop - Commandline Photoshop für Ascii-Bilder.
 *
 * Erlaubte Befehle: 
 * <ul>
 * <li>create width height charset - Erzeugt ein leeres Bild der Größe width x height. Muss als erster Befehl aufgerufen werden.
 * <li>binary threshold - wandelt das Bild in ein Binärbild um, sodass alle Zeichen, die im Zeichensatz vor dem Zeichen threshold stehen, auf das dunkelste Zeichen gesetzt werden und alle Zeichen ab und inklusive threshold auf das hellste Zeichen des Zeichensatzes gesetzt werden. 
 * <li>clear - löscht den gesamten Bildinhalt, alle Pixel des Bildes werden auf '.' gesetzt.
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c.
 * <li>filter type - wendet einen Filter auf das Bild an: Verfügbare Filtertypen: median;
 * <li>grow c - vergrößert den Bereich der Pixel mit dem Zeichen c. Dies bewirkt, dass alle Pixel, die nicht gesetzt sind und als Nachbarn einen Pixel mit dem Zeichen c haben, auf das Zeichen c gesetzt werden. 
 * <li>line x0 y0 x1 y1 c - Zeichnet eine Linie mit dem Zeichen c von Pos 0 bis Pos 1.
 * <li>load eof - Lest ein Ascii Bild bis zum Auftreten von eof ein. 
 * <li>print - gibt das ASCII-Bild gefolgt von einer Leerzeile aus. 
 * <li>replace oldChar newChar - ersetzt alle Vorkommen eines bestimmten Zeichens im Bild durch ein anderes Zeichen.
 * <li>transpose - Vertauscht Zeilen und Spalten.
 * <li>undo - macht einen Befehl rückgängig. 
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 8
 */
public class AsciiShop {

	/**
	 * Verarbeitet ein Kommando indem es in der entsprechenden Factory die Operation erstellt und ausführt.
	 */	
	public static void main(String[] args) {

		HashMap<String,Factory> factories = new HashMap<String,Factory>();

		factories.put("binary", new BinaryFactory()); 
		factories.put("clear", new ClearFactory()); 
		factories.put("fill", new FillFactory()); 
		factories.put("filter", new FilterFactory()); 
		factories.put("grow", new GrowRegionFactory()); 
		factories.put("line", new LineFactory()); 
		factories.put("load", new LoadFactory()); 
		factories.put("replace", new ReplaceFactory()); 
		factories.put("transpose", new TransposeFactory()); 
		
		try {
			AsciiImage image;
			AsciiStack history;
			Scanner sc = new Scanner(System.in);

			sc.findInLine("(\\w+)");
			MatchResult match = sc.match();
			String command = match.group().toLowerCase();

			if(command.equals("create")) {
				if(sc.findInLine("(\\d+) (\\d+) (\\S+)") == null) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				match = sc.match();
				int width = Integer.parseInt(match.group(1));
				int height = Integer.parseInt(match.group(2));
				String charset = match.group(3);

				if(width <= 0 || height <= 0 || charset.length() != AsciiImage.getUniqueChars(charset)) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				image = new AsciiImage(width,height,charset);
				history = new AsciiStack();
			}
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}

			while(sc.hasNextLine()) {
				sc.nextLine();
				if(sc.findInLine("(\\w+)") != null) {
					match = sc.match();
					command = match.group(1).toLowerCase();
					
					if(factories.containsKey(command)) {
						Factory factory = factories.get(command);
						Operation operation = factory.create(sc);
						history.push(image);
						image = operation.execute(image);
					}
					else if(command.equals("print")) {
						System.out.println(image + "\n");
					}
					else if(command.equals("undo")) {
						if(!history.empty()) {
							image = history.pop();
						}
						else{
							System.out.println("STACK EMPTY"); 
						}
					}
					else if(command.equals("histogram")) {
						AsciiImage histogram = Histogram.getHistogram(image);
						System.out.println(histogram + "\n");
					}
					else {
						System.out.println("UNKNOWN COMMAND"); 
						return;
					}
				}
			}
		}
		catch(FactoryException e) {
			System.out.println("INPUT MISMATCH"); 
		}
		catch(OperationException e) {
			System.out.println("OPERATION FAILED");
		}
	}
}

/**
 * Vergleicht zwei Character nach ihrer Helligkeit. 
 *
 * Diese ist durch ihre Ordnung in einem String definiert. Das hellste Zeichen befindet sich am Ende des Strings, das Dunkelste am Anfang.
 * Liefert unerwartete Ergebnisse wenn sich die chars für den Vergleich nicht im String befinden oder der String leer ist!
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class BrightnessComparator implements Comparator<Character> {
	
	private String charset;

	/**
	 * Erzeugt einen neuen Comparator durch einen String der die Ordnung der chars definiert.
	 *
	 * @param charset Ein nicht-leerer String der die Ordnung repräsentiert, beginnend mit dem Dunkelsten.
	 */
	public BrightnessComparator(String charset) {
		this.charset = charset;
	}

	/** 
	 * Führt einen Vergleich nach Helligkeit durch.
	 * 
	 * @param c1 Erstes Zeichen für den Vergleich. Muss in charset enthalten sein!
	 * @param c2 Zweites Zeichen für den Vergleich. Muss in charset enthalten sein!
	 * @return -1 wenn das erste Zeichen heller ist, 0 wenn die Zeichen gleich sind, +1 wenn das zweite Zeichen heller ist.
	 */
	public int compare(Character c1, Character c2) {
		return (int)Math.signum((double)charset.indexOf(c1) - (double)charset.indexOf(c2));
	}
}
