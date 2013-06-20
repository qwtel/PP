import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Iterator;

/**
 * Diese generische Klasse implementiert ein spezielles Set auf der Basis von LinkedHashSet mit der Besonderheit, dass in dem MetricSet mit Hilfe einer Metrik nach Objekten gesucht werden kann, die einem spezifizierten Objekt ähnlich sind. 
 *
 * @author Florian Klampfer <e1058208@student.tuwien.ac.at>
 * @version 1
 */
class MetricSet<E> extends LinkedHashSet<E> {
	
	/**
	 * Initialisiert ein leeres MetricSet. 
	 */
	public MetricSet() {}

	/**
	 * Initialisiert das MetricSet mit den Elementen aus c. 
	 *
	 * @param c Collection die Elemente für das Erzeugen eines neuen MetricSet enthält.
	 */
	public MetricSet(Collection<? extends E> c) {
		super(c);
	}

	/**
	 * Liefert ein neues MetricSet zurück, in dem nur die Elemente enthalten sind, die die minimale Distanz zum spezifizierten Element e haben.	 
	 * 
	 * @param e Das Element nachdem gesucht werden soll.
	 * @param m Metric die als Distanzmaß benutzt werden soll.
	 * @return Ein neues MetricSet welches nur die Elemente beinhaltet, die die minimale Distanz zum Element e haben. Kann auch nur ein Element sein.
	 */
	public MetricSet<E> search(E e, Metric<? super E> m) {
		MetricSet<E> result = new MetricSet<E>();
		Iterator<E> iter = this.iterator();

		int dmin = Integer.MAX_VALUE;

		while(iter.hasNext()) {
			E i = iter.next();
			int d = m.distance(e,i);

			if(d < dmin) {
				result = new MetricSet<E>();
				dmin = d;
			}
			if(d == dmin) {
				result.add(i);
			}
		}

		return result;
	}
}
