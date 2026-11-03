import java.util.Scanner;
public class barracks {
	private String introText;

	public barracks() {
		introText = "You open the door to the barracks with rotting bunks and crats piled high. The smell of mold and decay fills the air. You see a few items scattered around the room.";
	}

	public void enter() {
		System.out.println(introText);
        giveFirstItem();
	}

	public void giveFirstItem() {
        Scanner scanner = new Scanner(System.in);
		System.out.println("You walk your way over to a collapsed weapon rack and you sweep the dust and broken wood out of the way and find...(what do you find?)");
        String firstItem = scanner.nextLine();
        if (firstItem.equalsIgnoreCase("sword")) {
            System.out.println("You found a sword! You can use it to defend yourself.");
        } else if (firstItem.equalsIgnoreCase("Axe")) {
            System.out.println("You found a Axe!");
        } else if (firstItem.equalsIgnoreCase("Bow")) {
            System.out.println("You found a Bow and Arrows!");
        }else {
            System.out.println("You didn't find anything useful.");
        }
    }

	public void giveSecItem() {
		// Add the second item here.
	}

	public void giveThirdItem() {
		// Add the third item here.
	}

	public void processChoice(String choice) {
		// Process the player's choice here.
	}
}
