import java.util.Scanner;

/**
 * BarPlot liest Balkenbeschriftungen und Werte paarweise ein und gibt ein horizontales Balkendiagramm aus
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version A2
 */
public class BarPlot {

	public static final int LABEL_LENGTH = 8;
	public static final int BAR_LENGTH = 30;
	public static final char BAR_CHAR = '#';
	public static final char BAR_SEPARATOR = '|';
	
	/**
	 * Liest die Daten und Befehle ein und gibt das Ergebnis aus
	 */
	public static void main(String[] args) throws RuntimeException {
		Scanner sc = new Scanner(System.in);
		String label, bar;

		try {
			while(sc.hasNext()) {

				label = sc.next();

				if(sc.hasNextInt()) {
					bar = drawBar(label,sc.nextInt());
				}
				else if(sc.hasNextDouble()) {
					bar = drawBar(label,sc.nextDouble());
				}
				else {
					throw new RuntimeException();
				}

				System.out.println(bar);
			}
		}
		catch(RuntimeException e) {
			System.out.println("INPUT ERROR");
		}
		
	}

	/**
	 * Liefert einen String der Laenge n zurueck der nur aus dem Zeichen c besteht	
	 *
	 * @param c Zeichen 
	 * @param n Anzahl der Wiederhohlungen
	 * @return String der Laenge n aus Zeichen c 
	 */
	static String repeat(char c, int n) {
		String ret = "";

		for(int i=0; i<n; i++) {
			ret += c;	
		}

		return ret;
	}

	/**
	 * Liefert einen String zurueck der label beinhaltet aber genau n Zeichen lang ist   
	 *
	 * @param label Name des labels
	 * @param n Laenge des labels
	 * @return String der Laenge n mit label
	 */
	static String drawLabel(String label, int n) {
		String space = "";
		int length = label.length();	
		
		if(length > n) {
			label = label.substring(0,n);	
		}
		else {
			space = repeat(' ',n-length);
		}

		return label + space;
	}
	
	/**
	 * Generiert eine Zeile des Balkendiagramms mit absoluter Laenge 
	 *
	 * @param label Bezeichnung des Balkens
	 * @param value Bezeichnet die absolute Laenge des Balkens 
	 * @return Der generierte Balken als String
	 * @throws RuntimeException Wenn value groesser als die maximale Laenge des Balkens ist 
	 */
	static String drawBar(String label, int value) throws RuntimeException {
		if(value > BAR_LENGTH) {
			throw new RuntimeException(); 
		}

		String outputLabel = drawLabel(label, LABEL_LENGTH);
		String outputBar = repeat(BAR_CHAR,value) + repeat(' ',BAR_LENGTH-value);

		return outputLabel + BAR_SEPARATOR + outputBar + BAR_SEPARATOR;
	}

	/**
	 * Generiert eine Zeile des Balkendiagramms mit prozentueller Laenge
	 *
	 * @param label Bezeichnung des Balkens
	 * @param value Bezeichnet die prozentuelle Laenge des Balkens 
	 * @return Der generierte Balken als String
	 * @throws RuntimeException Wenn value groesser als 1.0 ist 
	 */	
	static String drawBar(String label, double value) throws RuntimeException {
		if(value > 1.0) {
			throw new RuntimeException();
		}

		int intValue = (int)Math.round(value*(double)BAR_LENGTH); 
		return drawBar(label,intValue);
	}
}
