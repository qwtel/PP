/**
 * Diese Klasse implementiert einen Stack, der seine Größe dynamisch anpasst.
 *
 * Er kann eine beliebige Anzahl an AsciiImage-Objekten speichern, wobei der Zugriff immer nur auf das oberste Element möglich ist.
 * Diese Implementierung nutzt intern die Klasse AsciiStackNode um mehrere Bilder in einer Liste zu verketten.
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 2
 */
class AsciiStack {

	private AsciiStackNode head;

	/**
	 * Erzeugt einen leeren Stack.
	 */
	public AsciiStack() {
		head = null;
	}

	/**
	 * Überprüft, ob der Stack leer ist. 
	 * 
	 * @return true wenn der Stack leer ist
	 */
	public boolean empty() {
		return head == null;
	}

	/**
	 * Gibt das oberste Element am Stack zurück und entfernt dieses. 
	 *
	 * @return das oberste Element am Stack wenn vorhanden, ansonsten null.
	 */
	public AsciiImage pop() {
		if(head != null) {
			AsciiImage value = head.image;
			head = head.next;
			return value;
		}
		return null;
	}

	/**
	 * Gibt das oberste Element am Stack zurück ohne es zu entfernen. 
	 *
	 * @return das oberste Element am Stack wenn vorhanden, ansonsten null.
	 */
	public AsciiImage peek() {
		if(head != null) {
			return head.image;
		}
		return null;
	}

	/**
	 * Legt ein AsciiImage oben auf den Stack. 
	 *
	 * @param img Das Bild das auf den Stapel gelegt werden soll
	 */
	public void push(AsciiImage img) {
		head = new AsciiStackNode(img, head);
	}
	
	/**
	 * Gibt die Anzahl der im Stack belegten Plätze zurück.
	 *
	 * @return Anzal der belegten Plätze
	 */
	public int size() {
		if(head == null) {
			return 0;
		}
		else {
			return head.size();
		}
	}

	/**
	 * Diese Klasse implementiert einen Knoten des Stacks. 
	 */
	private class AsciiStackNode {
		
		private AsciiImage image;
		private AsciiStackNode next;

		/**
		 * Inizialisiert den Listenknoten. 
		 */
		public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
			this.image = image;
			this.next = next;
		}

		/**
		 * Liefert die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste plus eins (für diesen Knoten). 
		 * 
		 * @return Anazahl der verbleibenden Knoten in der Liste + 1.
		 */
		public int size() {
			if(next == null) {
				return 1;
			}
			else {
				return 1 + next.size();
			}
		}
	}
}
