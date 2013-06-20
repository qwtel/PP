import java.util.Scanner;

class InputMismatchException extends RuntimeException {}
class OperationFailedException extends IndexOutOfBoundsException {}

/**
 * AsciiShop - Commandline Photoshop fuer Ascii-Bilder.
 *
 * Zeilenweises Einlesen eines Ascii-Bildes ueber die Standardeingabe.
 * Liest Befehle über die Commandline ein
 *
 * Mögliche Befehle: 
 * <ul>
 * <li>read n - Lest ein Ascii Bild der Höhe n ein
 * <li>fill x y c - Befüllt eine Fläche ausgehend von x/y mit dem Zeichen c
 * </ul>
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 3
 */
public class AsciiShop {
	
	/**
	 * Verarbeitet ein Kommando indem es die entsprechende Funktion aufruft.
	 *
	 * Gibt das gelesene Bild nach erfolgreicher Bearbeitung über die Standardausgabe aus.
	 *
	 * @throws InputMismatchException Wenn ein ungültige Eingabe vorgenommen wurde
	 * @throws OperationFailedException Wenn fill nicht aufgerufen werden kann
	 */	
	public static void main(String[] args) throws InputMismatchException, OperationFailedException {
		try {
			String[] image;

			Scanner sc = new Scanner(System.in);
			Scanner commandScanner = new Scanner(sc.nextLine());
			String command = commandScanner.next();

			/*
			 * Lest n Zeilen eines Ascii-Bildes über die Standardeingabe ein.
			 * nextLine wirft eine RuntimeException (die behandelt wird) wenn keine Zeilen mehr gelesen werden können.
			 * Eine InputMismatchException wird geworfen wenn die Zeilenlänge nicht mit der Bildlänger übereinstimmt.
			 */
			if(command.equals("read")) {
				int n = commandScanner.nextInt();

				image = new String[n];
				int fixedWidth = 0;

				for(int i=0; i<n; i++) {
					image[i] = sc.nextLine();

					if(i == 0) {
						fixedWidth = image[i].length();
					}

					if(image[i].length() != fixedWidth) {
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

				if(command.equals("fill")) {
					int x = commandScanner.nextInt();
					int y = commandScanner.nextInt();
					char c = commandScanner.next().charAt(0);
				
					if(isInBound(image,x,y)) {
						fill(image,x,y,c);
					}
					else {
						throw new OperationFailedException();
					}
				}
				else {
					throw new InputMismatchException();
				}
			}

			for(String s : image) {
				System.out.println(s);
			}
			System.out.println(image[0].length() + " " + image.length);
		}
		catch(OperationFailedException e) {
			System.out.println("OPERATION FAILED");
		}
		catch(RuntimeException e) {
			System.out.println("INPUT MISMATCH"); 
		}
	}

	/**
	 * Befüllt eine Fläche ausgehend von einer Kooridinate X/Y mit dem Zeichen c nach der Vierer-Nachbarschafts-Regel.
	 *
	 * Zur Bearbeitung wird der String in ein char-Array umgewandelt.
	 * Es wird VOR jedem rekursiven Aufruf geprüft ob die benachbarten Elemente ersetzt werden dürfen.
	 *
	 * @param image Das Ascii-Bild als String-Array.
	 * @param x Position in der Spalte beginnend bei 0.
	 * @param y Position in der Reihe beginnend bei 0.
	 * @param c Füllfarbe
	 */
	public static void fill(String[] image, int x, int y, char c) {
		char old = image[y].charAt(x);

		char[] row = image[y].toCharArray();
		row[x] = c;
		image[y] = new String(row);
		
		if((y-1)>=0 && image[y-1].charAt(x) == old) {
			fill(image,x,y-1,c);
		}
		if((x-1)>=0 && image[y].charAt(x-1) == old) {
			fill(image,x-1,y,c);
		}
		if((y+1)<image.length && image[y+1].charAt(x) == old) {
			fill(image,x,y+1,c);
		}
		if((x+1)<image[y].length() && image[y].charAt(x+1) == old) {
			fill(image,x+1,y,c);
		}
	}

	/**
	 * Hilfsfunktion die prüft ob sich eine gegebene Koordinate im Bild befundet
	 *
	 * @param image Das Ascii-Bild als String-Array
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return true wenn sich die Koordination in image befinden
	 */
	private static boolean isInBound(String[] image, int x, int y) {
		return (x>=0 && y>=0 && x<image[0].length() && y<image.length);
	}
}
