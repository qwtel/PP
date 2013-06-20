import java.util.Scanner;

class InputMismatchException extends RuntimeException {}
class OperationFailedException extends RuntimeException {}
class UnknownCommandException extends RuntimeException {}

/**
 * AsciiShop - Commandline Photoshop für Ascii-Bilder.
 *
 * Erlaubte Befehle: 
 * <ul>
 * <li>create width height - Erzeugt ein leeres Bild der Größe width x height. Muss als erster Befehl aufgerufen werden.
 * <li>centroid c - bestimmt den Schwerpunkt aller Pixel, die dem als Parameter definierten Zeichen (c) entsprechen und gibt das Ergebnis in der Form '(x,y)' in einer eigenen Zeile aus.
 * <li>clear - löscht den gesamten Bildinhalt, alle Pixel des Bildes werden auf '.' gesetzt.
 * <li>grow c - vergrößert den Bereich der Pixel mit dem Zeichen c. Dies bewirkt, dass alle Pixel, die nicht gesetzt sind und als Nachbarn einen Pixel mit dem Zeichen c haben, auf das Zeichen c gesetzt werden. 
 * <li>line x0 y0 x1 y1 c - Zeichnet eine Linie mit dem Zeichen c von Pos 0 bis Pos 1.
 * <li>load eof - Lest ein Ascii Bild bis zum Auftreten von eof ein. 
 * <li>print - gibt das ASCII-Bild gefolgt von einer Leerzeile aus. 
 * <li>replace oldChar newChar - ersetzt alle Vorkommen eines bestimmten Zeichens im Bild durch ein anderes Zeichen.
 * <li>transpose - Vertauscht Zeilen und Spalten.
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c.
 * <li>undo - macht einen Befehl rückgängig. 
 * <li>uniqueChars - Liefert die Anzahl der unterschiedlichen Zeichen des Bildes.
 * <li>flip-v - Spiegelt das Bild vertical.
 * <li>symmetric-h - Ermittelt ob es sich bei dem Bild um ein Palindrom handelt.
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 6
 */
public class AsciiShop {
	/**
	 * Verarbeitet ein Kommando indem es die entsprechende Funktion aufruft.
	 *
	 * @throws InputMismatchException Wenn ein ungültiges Kommando übergeben wurde.
	 * @throws OperationFailedException Wenn fill nicht aufgerufen werden kann.
	 */	
	public static void main(String[] args) throws InputMismatchException, OperationFailedException {

		try {
			AsciiImage image;
			AsciiStack history;

			Scanner sc = new Scanner(System.in);
			Scanner commandScanner = new Scanner(sc.nextLine());
			String command = commandScanner.next();
			
			if(command.equals("create")) {
				int width = commandScanner.nextInt();
				int height = commandScanner.nextInt();

				if(width <= 0 || height <= 0) {
					throw new InputMismatchException();
				}

				image = new AsciiImage(width,height);
				history = new AsciiStack(3);
			}
			else {
				throw new InputMismatchException();
			}

			while(sc.hasNextLine()) {
				commandScanner = new Scanner(sc.nextLine());
				command = commandScanner.next();
				
				if(command.equals("clear")) {
					history.push(new AsciiImage(image));
					image.clear();
				}
				else if(command.equals("centroid")) {
					char c = commandScanner.next().charAt(0);
					System.out.println(image.getCentroid(c));
				}
				else if(command.equals("grow")) {
					history.push(new AsciiImage(image));
					char c = commandScanner.next().charAt(0);
					image.growRegion(c);
				}
				else if(command.equals("line")) {
					history.push(new AsciiImage(image));
					int x0 = commandScanner.nextInt();
					int y0 = commandScanner.nextInt();
					int x1 = commandScanner.nextInt();
					int y1 = commandScanner.nextInt();
					char c = commandScanner.next().charAt(0);
					
					if(image.isInBound(x0,y0) && image.isInBound(x1,y1)) {
						image.drawLine(x0,y0,x1,y1,c);
					}
					else {
						throw new InputMismatchException();
					}
				}
				else if(command.equals("load")) {
					history.push(new AsciiImage(image));
					String eof = commandScanner.next();
					int x,y;
					
					for(y=0; y<image.getHeight()+1; y++) {
						String line = sc.nextLine();

						if(line.equals(eof)) {
							break;
						}

						if(line.length() != image.getWidth()) {
							throw new InputMismatchException();
						}

						for(x=0; x<image.getWidth(); x++) {
							image.setPixel(x,y,line.charAt(x));
						}
					}

					if(y != image.getHeight()) {
						throw new InputMismatchException();
					}
				}
				else if(command.equals("print")) {
					System.out.println(image + "\n");
				}
				else if(command.equals("replace")) {
					history.push(new AsciiImage(image));
					char oldChar = commandScanner.next().charAt(0);
					char newChar = commandScanner.next().charAt(0);
					image.replace(oldChar,newChar);
				}
				else if(command.equals("transpose")) {
					history.push(new AsciiImage(image));
					image.transpose();
				}
				else if(command.equals("fill")) {
					history.push(new AsciiImage(image));
					int x = commandScanner.nextInt();
					int y = commandScanner.nextInt();
					char c = commandScanner.next().charAt(0);

					if(image.isInBound(x,y)) {
						image.fill(x,y,c);
					}
					else {
						throw new OperationFailedException();
					}
				}
				else if(command.equals("undo")) {
					if(!history.empty()) {
						image = new AsciiImage(history.pop());
						System.out.println("STACK USAGE "+history.size()+"/"+history.capacity());
					}
					else{
						System.out.println("STACK EMPTY"); 
					}
				}
				else if(command.equals("uniqueChars")) {
					System.out.println(image.getUniqueChars());
				}
				else if(command.equals("flip-v")) {
					history.push(new AsciiImage(image));
					image.flipV();
				}
				else if(command.equals("symmetric-h")) {
					System.out.println(image.isSymmetricH());
				}
				else {
					throw new UnknownCommandException();
				}

			}
		}
		catch(OperationFailedException e) {
			System.out.println("OPERATION FAILED");
		}
		catch(UnknownCommandException e) {
			System.out.println("UNKNOWN COMMAND");
		}
		catch(RuntimeException e) {
			System.out.println("INPUT MISMATCH"); 
		}
	}
}
