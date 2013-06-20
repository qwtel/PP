import java.util.Scanner;

/**
 * AsciiShop - Commandline Photoshop fuer Ascii-Bilder.
 *
 * Zeilenweises Einlesen eines Ascii-Bildes ueber die Standardeingabe.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version A1
 */
public class AsciiShop {

	private static Scanner sc = new Scanner(System.in);
	private static int width = 0;
	private static int currentWidth = 0;
	private static int height = 0;

	public static void main(String[] args) {

		/*
		 * Zeilenweises Lesen der Eingabe.
	 	 * Mit der ersten Zeile wird die Weite des Bildes festgelegt
		 * Wird die Weite nicht eingehalten bricht das Programm mit Fehlermeldung ab
		 */
		while(sc.hasNextLine()) {
			currentWidth = sc.nextLine().length();

			if(height == 0) {
				width = currentWidth;
			}
			height++;

			if(currentWidth !=  width) {
				System.out.println("INPUT MISMATCH");
				return;
			}
		}

		/*
		 * Ende der Eingabe fehlerfrei erreicht 
		 * Ausgabe von Hoehe und Breite 
		 */
		System.out.println(width + " " + height);
	}
}
