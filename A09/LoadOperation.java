import java.util.Scanner;

/**
 * Lädt zeilenweise vorliegende Bilddaten in ein AsciiImage.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
public class LoadOperation implements Operation {

	private Scanner sc;

	/**
	 * Erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten. 
	 *
	 * @param data Bilddaten als String durch Zeilenumbrüche ('\n') getrennt.
	 */
	public LoadOperation(String data) {
		sc = new Scanner(data);
	}

	/**
	 * Gibt ein neues AsciiImage zurück, das von Größe und Zeichensatz dem übergebenen AsciiImage entspricht und in das die Daten geladen wurden.
	 *
	 * @param img Das Bild auf dem die Operation aufgeführt werden soll.
	 * @return Das bearbeitete Bild.
	 * @throws OperationException Wenn zu wenige oder zu viele Daten bzw. ungültige Zeichen gelesen werden.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		AsciiImage image = new AsciiImage(img);
		int x,y;

		for(y=0; sc.hasNextLine(); y++) {
			String line = sc.nextLine();

			Debug.log(line.length()+" "+image.getWidth());

			if(line.length() != image.getWidth()) {
				throw new OperationException("Breite nicht eingehalten");
			}
			
			for(x=0; x<image.getWidth(); x++) {
				if(!image.getCharset().contains(""+line.charAt(x))) {
					throw new OperationException("Zeichen nicht im Zeichensatz");
				}
				image.setPixel(x,y,line.charAt(x));
			}

		}
		
		if(y != image.getHeight()) {
			throw new OperationException("Höhe nicht eingehalten");
		}

		return image;
	}
}
