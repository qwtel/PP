import java.util.ArrayList;

/*
 * 1. Aufgabe: Typbeziehungen
 * 
 * Der Inhalt dieser Datei wird nicht bewertet.
 */
public class Zoo {
	
	public static void main(String[] args) {
		
		ArrayList<Animal> zoo = new ArrayList<Animal>();
		zoo.add(new Tiger());
		zoo.add(new Lion());
		zoo.add(new Sheep());
		zoo.add(new Elephant());

		for(Animal a : zoo) {
			System.out.println("Eats meat: " + a.eatsMeat() + " Daily quantity: " + a.dailyFoodQuantity());
		}
		
	}
	
}
