import java.util.Arrays;

/**
 * Erzeugt Histogramme für AsciiImages.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class Histogram {

	private static final int HEIGHT = 16;
	private static final char BAR_CHAR = '#';
	private static final char BG_CHAR = '.';

	/**
	 * Erzeugt ein neues Histogramm für ein gegebenes AsciiImage.
	 *
	 * @param img Das AsciiImage für das ein Histogramm erstellt werden soll.
	 * @return Das Histogramm als AsciiImage.
	 */	
	public static AsciiImage getHistogram(AsciiImage img) throws OperationException {
		String charset = img.getCharset();
		String charsetHistogram = BAR_CHAR + "0123456789" + charset + BG_CHAR;
		charsetHistogram = AsciiImage.getUniqueCharsAsString(charsetHistogram);
		int widthHistogram = charset.length() + 3;

		AsciiImage histogram = new AsciiImage(widthHistogram,HEIGHT,charsetHistogram);

		int[] occurences = new int[charset.length()];
		int maxAmount = 0;
		
		/*
		 * Speichere die Anzahl aller Zeichen im Bild und füge Beschriftung (charset) unten hinzu.
		 */
		for(int i=0; i<charset.length(); i++) {
			char c = charset.charAt(i);
			int amount = img.getPointList(c).size();
			occurences[i] = amount;

			if(amount > maxAmount) {
				maxAmount = amount;
			}

			histogram.setPixel(3+i,HEIGHT-1,c);	
		}

		/*
		 * Prozent-Beschriftung links
		 */
		int size = img.getWidth() * img.getHeight();	
		double maxPercentage = (double)maxAmount/(double)size*100.0;
		double stepPercentage = maxPercentage/(HEIGHT-1);

		for(int i=0; i<HEIGHT; i+=2) {
			String p = String.format("%3d", Math.round(stepPercentage*(HEIGHT-1-i)));
			for(int j=0; j<p.length(); j++) {
				if(p.charAt(j) != ' ') {
					histogram.setPixel(j,i,p.charAt(j));
				}
			}
		}

		/*
		 * Zeichne Balken
		 */
		int column = 3;
		for(int i=0; i<charset.length(); i++) {
			int amount = occurences[i];

			if(amount > 0) {
				double scale = (double)amount/(double)maxAmount;
				int barHeight = (int)Math.ceil(scale*(HEIGHT-1));

				int from = HEIGHT-2;	
				int to = HEIGHT-2-barHeight+1;
				Operation op = new LineOperation(column,from,column,to,BAR_CHAR);
				histogram = op.execute(histogram);
			}
			column++;
		}

		return histogram;
	}
}
