/**
 * Diese Klasse implementiert einen Stack, der seine Größe dynamisch anpasst.
 *
 * Er kann eine beliebige Anzahl an AsciiImage-Objekten speichern, wobei der Zugriff immer nur auf das oberste Element möglich ist.
 * Diese Implementierung nutzt intern ein Array zum Speichern der Elemente. 
 * Der Stackpointer zeigt immer auf das nächste freie Element.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class AsciiStack {

	private AsciiImage[] array;
	private int increment;
	private int pointer;
	
	/**
	 * Erzeugt einen Stack, der initial increment Elemente speichern kann.
	 *
	 * Das Parameter increment gibt auch an, um wieviel der Stack bei Bedarf vergrößert bzw. verkleinert werden soll.
	 *
	 * @param increment Initialwert und Größeneinheit des Stacks
	 */
	public AsciiStack(int increment) {
		this.array = new AsciiImage[increment];
		this.increment = increment;
		this.pointer = 0;
	}

	/**
	 * Gibt die Anzahl der im Stack bereitstehenden Plätze zurück.
	 *
	 * Aufgrund der Vorgehensweise bei Vergößerung und Verkleinerung, ist das Ergebnis dieser Methode immer ein Vielfaches von increment. 
	 *
	 * @return Anzahl freier Plätze im Stack.
	 */
	public int capacity() {
		return array.length;
	}

	/**
	 * Überprüft, ob der Stack leer ist. 
	 * 
	 * @return true wenn der Stack leer ist
	 */
	public boolean empty() {
		return pointer == 0;
	}

	/**
	 * Gibt das oberste Element am Stack zurück und entfernt dieses. 
	 *
	 * Sind nach dem Entfernen mehr als increment Plätze leer, wird der Stack um increment verkleinert. 
	 * Allerdings wird der Stack nie kleiner als increment sein. 
	 *
	 * @return das oberste Element am Stack wenn vorhanden, ansonsten null.
	 */
	public AsciiImage pop() {
		if(peek() != null) {
			AsciiImage copy = new AsciiImage(peek());
			pointer--;

			if(array.length-pointer > increment) {
				changeSize(array.length - increment);
			}

			array[pointer] = null;
			return copy;
		}
		return null;
	}

	/**
	 * Gibt das oberste Element am Stack zurück ohne es zu entfernen. 
	 *
	 * @return das oberste Element am Stack wenn vorhanden, ansonsten null.
	 */
	public AsciiImage peek() {
		if(!empty()) {
			return array[pointer-1];
		}
		return null;
	}

	/**
	 * Legt ein AsciiImage oben auf den Stack. 
	 *
	 * Ist der Stack zu diesem Zeitpunkt voll, wird der Stack um increment vergrößert. 
	 *
	 * @param img Das Bild das auf den Stapel gelegt werden soll
	 */
	public void push(AsciiImage img) {
		if(pointer >= array.length) {
			changeSize(array.length + increment);
		}
		array[pointer] = img;
		pointer++;
	}
	
	/**
	 * Gibt die Anzahl der im Stack belegten Plätze zurück.
	 *
	 * @return Anzal der belegten Plätze
	 */
	public int size() {
		return pointer;
	}

	/**
	 * Verändert die Größe des Arrays.
	 *
	 * @param newSize Gewünschte neue Größe des Arrays.
	 */
	private void changeSize(int newSize) {
		int copySize = newSize < array.length ? newSize : array.length;
		
		AsciiImage[] newArray = new AsciiImage[newSize];
		System.arraycopy(array, 0, newArray, 0, copySize);
		array = newArray;
	}
}
