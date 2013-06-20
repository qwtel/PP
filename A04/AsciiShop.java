import java.util.Scanner;

class InputMismatchException extends RuntimeException {}
class OperationFailedException extends RuntimeException {}

/**
 * AsciiShop - Commandline Photoshop für Ascii-Bilder.
 *
 * Zeilenweises Einlesen eines Ascii-Bildes über die Standardeingabe.
 * Liest Befehle über die Commandline ein um das Bild zu barbeiten.
 *
 * Mögliche Befehle: 
 * <ul>
 * <li>read n - Lest ein Ascii Bild der Höhe n ein
 * <li>uniqueChars - Liefert die Anzahl der unterschiedlichen Zeichen des Bildes
 * <li>flip-v - Spiegelt das Bild vertical
 * <li>transpose - Vertauscht Zeilen und Spalten
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c
 * <li>symmetric-h - Ermittelt ob es sich bei dem Bild um ein Palindrom handelt 
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 4
 */
public class AsciiShop {
	/**
	 * Verarbeitet ein Kommando indem es die entsprechende Funktion aufruft.
	 *
	 * Gibt das gelesene Bild nach erfolgreicher Bearbeitung über die Standardausgabe aus und kümmert sich um die Fehlerbehandlung.
	 *
	 * @throws InputMismatchException Wenn ein ungültiges Kommando übergeben wurde.
	 * @throws OperationFailedException Wenn fill nicht aufgerufen werden kann.
	 */	
	public static void main(String[] args) throws InputMismatchException, OperationFailedException {
		try {
			AsciiImage image = new AsciiImage();

			Scanner sc = new Scanner(System.in);
			Scanner commandScanner = new Scanner(sc.nextLine());
			String command = commandScanner.next();

			if(command.equals("read")) {
				int n = commandScanner.nextInt();

				for(int i=0; i<n; i++) {
					if(image.addLine(sc.nextLine()) == false) {
						throw new InputMismatchException();
					}
				}
			}
			else {
				throw new InputMismatchException();
			}

			while(sc.hasNextLine()) {
				commandScanner = new Scanner(sc.nextLine());
				command = commandScanner.next();
				
				if(command.equals("uniqueChars")) {
					System.out.println(image.getUniqueChars());
				}
				else if(command.equals("flip-v")) {
					image.flipV();
				}
				else if(command.equals("transpose")) {
					image.transpose();
				}
				else if(command.equals("fill")) {
					int x = commandScanner.nextInt();
					int y = commandScanner.nextInt();
					char c = commandScanner.next().charAt(0);

					if(x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {
						image.fill(x,y,c);
					}
					else {
						throw new OperationFailedException();
					}
				}
				else if(command.equals("symmetric-h")) {
					System.out.println(image.isSymmetricH());
				}
				else {
					throw new InputMismatchException();
				}
			}
			System.out.println(image.toString());
			System.out.println(image.getWidth() + " " + image.getHeight());
		}
		catch(OperationFailedException e) {
			System.out.println("OPERATION FAILED");
		}
		catch(RuntimeException e) {
			System.out.println("INPUT MISMATCH"); 
		}
	}
}
