import java.util.Collections;

/*
 * 3. Aufgabe: Rekursion und Collections
 *
 * DER INHALT DIESER DATEI WIRD BEWERTET!
 */

import java.util.HashMap;
 
/**
 * Repräsentiert eine Box.
 * 
 * Eine Box hat eine durch einen String definierte Farbe und 
 * kann eine weitere Box beinhalten.
 */
public class Box {

	private String color;	
	private Box content;
	
	/**
	 * Initialisiert eine neue Box mit der angegebenen Farbe.
	 * 
	 * @param color Die Farbe der Box als String
	 */
	public Box(String color) {
		this.color = color;
	}
	
	/**
	 * Legt die übergebene Box in diese Box hinein. Falls sich
	 * bereits eine Box darin befindet, wird diese überschrieben.
	 * 
	 * @param box Die Box, die hineingelegt werden soll
	 */	 
	public void insert(Box box) {	
		this.content = box;
	}
	
	/**
	 * Gibt die häufigste Farbe unter den Boxen zurück. Hierbei
	 * werden alle in dieser Box enthaltenenen Boxen, sowie diese
	 * Box selbst berücksichtigt.
	 * Sie können davon ausgehen, dass es eine Farbe gibt, die am 
	 * häufigsten vorkommt.
	 * 
	 * @return Die häufigst vorkommende Farbe als String
	 */
	public String getMostFrequentColor() {
		HashMap<String, Integer> count = countColors();
		
		int max = 0;
		String color = new String(); 

		for(String key : count.keySet()) {
			if(count.get(key) > max) {
				max = count.get(key);
				color = key;
			}
		}
		return color;
	}
	
	/**
	 * Gibt eine HashMap zurück, in der zu jeder vorkommende Farbe, 
	 * die Häufigkeit steht. Die Methode berücksichtigt alle enthaltenen
	 * Boxen, sowie diese Box. Die enthaltenen Boxen sollen rekursiv 
	 * durchlaufen werden.
	 * 
	 * @return Eine HashMap mit den Vorkommen der einzelnen Farben
	 */
	public HashMap<String, Integer> countColors() {
		
		if(content == null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put(color,1);
			return map;
		}

		HashMap<String, Integer> map = content.countColors();

		if(map.containsKey(color)) {
			int v = map.get(color);
			map.put(color,++v);
		}
		else {
			map.put(color,1);
		}
		return map;
		
	}
	
}
