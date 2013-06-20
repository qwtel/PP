import java.util.Arrays;

/**
 * Diese Klasse glättet ein Bild mit einem 3x3-Medianfilter.
 * 
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 3
 */
public class MedianOperation extends FilterOperation {
	
	/**
	 * Erzeugt eine neue MedianOperation
	 *
	 * @param blockGenerator Ein BlockGenerator welcher die Größe des Filter und die Randbehandlung spezifiziert.
	 */
	public MedianOperation(BlockGenerator blockGenerator) {
		super(blockGenerator);
	}

	/**
	 * Führt mit dem übergebenen Block den Medianwertfilter aus. 
	 *
	 * @param values Block für den der Median bestimmt werden soll.
	 * @return Der Median für den übergebenen Block.
	 */
	public int filter(int[] values) {
		Arrays.sort(values);
		return values[values.length/2]; 
	}
}
