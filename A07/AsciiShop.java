import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * AsciiShop - Commandline Photoshop für Ascii-Bilder.
 *
 * Erlaubte Befehle: 
 * <ul>
 * <li>create width height charset - Erzeugt ein leeres Bild der Größe width x height. Muss als erster Befehl aufgerufen werden.
 * <li>clear - löscht den gesamten Bildinhalt, alle Pixel des Bildes werden auf '.' gesetzt.
 * <li>grow c - vergrößert den Bereich der Pixel mit dem Zeichen c. Dies bewirkt, dass alle Pixel, die nicht gesetzt sind und als Nachbarn einen Pixel mit dem Zeichen c haben, auf das Zeichen c gesetzt werden. 
 * <li>line x0 y0 x1 y1 c - Zeichnet eine Linie mit dem Zeichen c von Pos 0 bis Pos 1.
 * <li>load eof - Lest ein Ascii Bild bis zum Auftreten von eof ein. 
 * <li>print - gibt das ASCII-Bild gefolgt von einer Leerzeile aus. 
 * <li>replace oldChar newChar - ersetzt alle Vorkommen eines bestimmten Zeichens im Bild durch ein anderes Zeichen.
 * <li>transpose - Vertauscht Zeilen und Spalten.
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c.
 * <li>undo - macht einen Befehl rückgängig. 
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 7
 */
public class AsciiShop {
	/**
	 * Verarbeitet ein Kommando indem es die entsprechende Funktion aufruft.
	 *
	 * Diese Methode Erzeugt ein AsciiImage, kümmert sich um die Ein- und Ausgabe und die Fehlerbehandlung.
	 */	
	public static void main(String[] args) throws OperationException {

		try {
			AsciiImage image;
			AsciiStack history;
			Scanner sc = new Scanner(System.in);

			sc.findInLine("(\\w+)");
			MatchResult match = sc.match();
			String command = match.group();
			
			if(command.equals("create")) {
				if(sc.findInLine("(\\d+) (\\d+) (\\S+)") == null) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				match = sc.match();
				int width = Integer.parseInt(match.group(1));
				int height = Integer.parseInt(match.group(2));
				String charset = match.group(3);

				if(width <= 0 || height <= 0) {
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
					command = match.group(1);

					if(command.equals("clear")) {
						history.push(image);
						ClearOperation op = new ClearOperation();
						image = op.execute(image);
					}
					else if(command.equals("filter")) {
						if(sc.findInLine("(\\w+)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						match = sc.match();
						String type = match.group(1);

						if(type.equals("median")) {
							history.push(image);
							MedianOperation op = new MedianOperation();
							image = op.execute(image);
						}
						else {
							System.out.println("INPUT MISMATCH");
							return;
						}
					}
					else if(command.equals("grow")) {
						if(sc.findInLine("(\\S)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						match = sc.match();
						char c = match.group(1).charAt(0);

						history.push(image);
						GrowRegionOperation op = new GrowRegionOperation(c);
						image = op.execute(image);
					}
					else if(command.equals("line")) {
						if(sc.findInLine("(\\d+) (\\d+) (\\d+) (\\d+) (\\S)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						match = sc.match();
						int x0 = Integer.parseInt(match.group(1));
						int y0 = Integer.parseInt(match.group(2));
						int x1 = Integer.parseInt(match.group(3));
						int y1 = Integer.parseInt(match.group(4));
						char c = match.group(5).charAt(0);
						
						history.push(image);
						LineOperation op = new LineOperation(x0,y0,x1,y1,c);
						image = op.execute(image);
					}
					else if(command.equals("load")) {
						if(sc.findInLine("(\\S+)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}
						
						match = sc.match();
						String eof = match.group(1);

						Debug.log(eof);

						sc.nextLine();
						String line = sc.nextLine();
						String lines = line;

						while(sc.hasNext()) {
							line = sc.next();

							if(line.equals(eof)) {
								break;
							}

							lines += "\n" + line;
						}

						Debug.log(lines);

						if(!line.equals(eof)) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						history.push(image);
						LoadOperation op = new LoadOperation(lines);
						image = op.execute(image);
					}
					else if(command.equals("print")) {
						System.out.println(image + "\n");
					}
					else if(command.equals("replace")) {
						if(sc.findInLine("(\\S+) (\\S+)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						match = sc.match();
						char oldChar = match.group(1).charAt(0);
						char newChar = match.group(2).charAt(0);
						
						history.push(image);
						ReplaceOperation op = new ReplaceOperation(oldChar, newChar);
						image = op.execute(image);
					}
					else if(command.equals("transpose")) {
						history.push(image);
						TransposeOperation op = new TransposeOperation();
						image = op.execute(image);
					}
					else if(command.equals("fill")) {
						if(sc.findInLine("(\\d+) (\\d+) (\\S)") == null) {
							System.out.println("INPUT MISMATCH");
							return;
						}

						match = sc.match();
						int x = Integer.parseInt(match.group(1));
						int y = Integer.parseInt(match.group(2));
						char c = match.group(3).charAt(0);

						history.push(image);
						FillOperation op = new FillOperation(x,y,c);
						image = op.execute(image);
					}
					else if(command.equals("undo")) {
						if(!history.empty()) {
							image = history.pop();
						}
						else{
							System.out.println("STACK EMPTY"); 
						}
					}
					else {
						System.out.println("UNKNOWN COMMAND");
						return;
					}
				}
			}
		}
		catch(OperationException e) {
			System.out.println("OPERATION FAILED");
		}
	}
}

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


