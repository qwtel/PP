import java.util.ArrayList;

/**
 * Diese Klasse repräsentiert eine Playlist aus der 
 * Musiksammlung. Eine Playlist ist eine Ansammlung 
 * von Songs, die in einer bestimmten Reihenfolge 
 * (jeder Song jedoch nur einmal) vorkommen dürfen. 
 */
public class Playlist {

	private ArrayList<Song> pl;

	/**
	 * Initialisiert diese Playlist, die keine Lieder enthält. 
	 *
	 */
	public Playlist() {

		pl = new ArrayList<Song>();

	}

	/**
	 * Dieser Kopierkonstruktor initialisiert die Playlist, 
	 * die alle Lieder einer bereits bestehenden Instanz 
	 * enthält. Änderungen einer der beiden Playlists 
	 * dürfen sich auf der jeweils anderen nicht auswirken. 
	 *
	 * @param original Die Playlist, welche kopiert werden soll 
	 */
	public Playlist(Playlist original) {

		pl = new ArrayList<Song>(original.pl);

	}

	/**
	 * Dieser Kopierkonstruktor initialisiert die Playlist, 
	 * die eine Aneinanderkettung von zwei bestehenden 
	 * Playlists darstellt. Dabei sollen zuerst die 
	 * Lieder aus playlist1, dann die Lieder aus der 
	 * playlist2 übernommen werden. Änderungen einer 
	 * der drei danach bestehenden Playlists dürfen 
	 * sich auf den jeweils anderen nicht auswirken. 
	 *
	 * @param playlist1 Die erste Playlist, deren Songs 
	 * 	in die neue Playlist übernommen werden sollen 
	 * @param playlist2 Die zweite Playlist, deren Songs 
	 * 	in die neue Playlist übernommen werden sollen 
	 */
	public Playlist(Playlist playlist1, Playlist playlist2) {

		this(playlist1);

		for(Song s : playlist2.pl) {
			addSong(s);
		}

	}

	/**
	 * Fügt einen Song an die Playlist an; der Song 
	 * soll dabei ans Ende der Playlist gestellt werden. 
	 * Eine Playlist darf jedoch nicht den selben Song 
	 * doppelt enthalten, stellen Sie also sicher, dass 
	 * die Methode addSong dies überprüft. Hierbei ist 
	 * es ausreichend zu überprüfen, ob genau das selbe 
	 * Objekt (die selbe Referenz) bereits gespeichert 
	 * ist. Sie können daher problemlos eine entsprechende 
	 * Methode der Klasse  nutzen. addSong soll true 
	 * zurückgeben, falls das Hinzufügen erfolgreich 
	 * war, und false, falls nicht. 
	 *
	 * @param song Der Song, der zur Playlist 
	 * 	hinzugefügt werden soll 
	 *
	 * @return Ein Wert, der Angibt, ob das 
	 * 	Hinzufügen erfolgreich war 
	 */
	public boolean addSong(Song song) {

		if(pl.contains(song) == false) {
			pl.add(song);
			return true;
		}

		return false; 

	}

	/**
	 * Erzeugt aus der bestehenden Playlist eine zweite 
	 * Playlist, welche nur Lieder des durch artist 
	 * angegebenen Interpreten enthält. 
	 *
	 * @param artist Der Interpret, dessen Lieder in 
	 * 	die neue Playlist übernommen werden sollen 
	 *
	 * @return Die neu erzeugte Playlist 
	 */
	public Playlist extractPlaylistByArtist(String artist) {

		Playlist byArtist = new Playlist();

		for(Song s : pl) {
			if(s.getArtist().equals(artist)) {
				byArtist.addSong(s);
			}
		}

		return byArtist; 

	}

	/**
	 * Zählt, wie viele Lieder des durch artist angegebenen 
	 * Interpreten in der Playlist enthalten sind. 
	 *
	 * @param artist Der Interpret, dessen 
	 * 	Lieder gezählt werden sollen 
	 *
	 * @return Die Anzahl der Lieder des 
	 * 	Interpreten in der Playlist 
	 */
	public int countSongsByArtist(String artist) {

		return extractPlaylistByArtist(artist).pl.size();

	}

	/**
	 * Erstellt eine lesbare Form der Playlist. Diese 
	 * besteht aus einer Liste der String-Repräsentationen 
	 * der Songs, sowie in der letzten Zeile die Gesamtlauftzeit 
	 * der Playlist. 
	 *  
	 * ERWARTETE FORM DER RÜCKGABE:
	 * Shine On You Crazy Diamond - Pink Floyd (810 sec) 
	 * Mixtape - Jamie Cullum (299 sec) 
	 * My Only One - Amy MacDonald (212 sec) 
	 * Beats For You - Annie Stettin (245 sec) 
	 * 1566 sec 
	 *
	 * @return Eine lesbare Repräsentation der Playlist 
	 */
	public String toString() {

		String res = new String();
		int dur = 0;

		for(Song s : pl) {
			res += s + "\n";	
			dur += s.getDuration();
		}

		return res + dur; 

	}

}
