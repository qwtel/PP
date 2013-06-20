/**
 * Diese Klasse glättet ein Bild mit einem 3x3-Mittelwertfilter.
 * 
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 2
 */
public class AverageOperation extends FilterOperation {
	
	/**
	 * Erzeugt eine neue AverageOperation
	 *
	 * @param blockGenerator Ein BlockGenerator welcher die Größe des Filter und die Randbehandlung spezifiziert.
	 */
	public AverageOperation(BlockGenerator blockGenerator) {
		super(blockGenerator);
	}

	/**
	 * Führt mit dem übergebenen Block den Mittelwertfilter aus. 
	 *
	 * @param values Block für den der Mittelwert bestimmt werden soll.
	 * @return Der arithmetische Mittelwert für den übergebenen Block.
	 */
	public int filter(int[] values) {
		int sum=0;
		for(int i=0; i<values.length; i++) {
			sum += values[i];
		}
		return (int)Math.round((double)sum/(double)values.length);
	}
}

