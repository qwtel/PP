import java.util.Scanner;

class InputMismatchException extends RuntimeException {}
class OperationFailedException extends RuntimeException {}
class UnknownCommandException extends RuntimeException {}

/**
 * AsciiShop - Commandline Photoshop für Ascii-Bilder.
 *
 * Zeilenweises Einlesen eines Ascii-Bildes über die Standardeingabe.
 * Liest Befehle über die Commandline ein um das Bild zu barbeiten.
 *
 * Erlaubte Befehle: 
 * <ul>
 * <li>create width height - Erzeugt ein leeres Bild der Größe width x height. Muss als erster Aufgerufen werden.
 * <li>clear - löscht den gesamten Bildinhalt, alle Pixel des Bildes werden auf ‘.’ gesetzt.
 * <li>line x0 y0 x1 y1 c - Zeichnet eine Linie mit dem Zeichen c von Pos 0 bis Pos 1.
 * <li>load eof - Lest ein Ascii Bild bis zum Auftreten von eof ein. 
 * <li>print - gibt das ASCII-Bild gefolgt von einer Leerzeile aus. 
 * <li>replace oldChar newChar - ersetzt alle Vorkommen eines bestimmten Zeichens im Bild durch ein anderes Zeichen.
 * <li>transpose - Vertauscht Zeilen und Spalten.
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c.
 * <li>uniqueChars - Liefert die Anzahl der unterschiedlichen Zeichen des Bildes
 * <li>flip-v - Spiegelt das Bild vertical
 * <li>symmetric-h - Ermittelt ob es sich bei dem Bild um ein Palindrom handelt 
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 5
 */
public class AsciiShop {
	/**
	 * Verarbeitet ein Kommando indem es die entsprechende Funktion aufruft.
	 *
	 * @throws InputMismatchException Wenn ein ungültiges Kommando übergeben wurde.
	 * @throws OperationFailedException Wenn fill nicht aufgerufen werden kann.
	 */	
	public static void main(String[] args) throws InputMismatchException, OperationFailedException {

		/*
		 * Hilfvariable die entscheidet ob bei der Ausgabe ein Absatz eingefügt werden muss.
		 */
		boolean newParagraph = false;

		try {
			AsciiImage image;

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
			}
			else {
				throw new InputMismatchException();
			}

			while(sc.hasNextLine()) {
				commandScanner = new Scanner(sc.nextLine());
				command = commandScanner.next();
				
				if(command.equals("clear")) {
					image.clear();
				}
				else if(command.equals("line")) {
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
					if(newParagraph) {
						System.out.print("\n");
					}
					System.out.println(image);
					newParagraph = true;
				}
				else if(command.equals("replace")) {
					char oldChar = commandScanner.next().charAt(0);
					char newChar = commandScanner.next().charAt(0);
					image.replace(oldChar,newChar);
				}
				else if(command.equals("transpose")) {
					image.transpose();
				}
				else if(command.equals("fill")) {
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
				else if(command.equals("uniqueChars")) {
					System.out.println(image.getUniqueChars());
				}
				else if(command.equals("flip-v")) {
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
			if(newParagraph) {
				System.out.print("\n");
			}
			System.out.println("OPERATION FAILED");
		}
		catch(UnknownCommandException e) {
			if(newParagraph) {
				System.out.print("\n");
			}
			System.out.println("UNKNOWN COMMAND");
		}
		catch(RuntimeException e) {
			if(newParagraph) {
				System.out.print("\n");
			}
			System.out.println("INPUT MISMATCH"); 
		}
	}
}
