import java.util.Scanner;
public class barracks {
	private String introText;
	private Scanner scanner;
    private boolean hasGivenFirstItem = false;
    private boolean hasGivenSecondItem = false;
    private boolean hasGivenThirdItem = false;

	public barracks() {
		introText = "You open the door to the barracks with rotting bunks and crats piled high. The smell of mold and decay fills the air. You see a few items scattered around the room.";
		scanner = new Scanner(System.in);
	}

	public void enter() {
		System.out.println(introText);
		System.out.println("You must search each section before moving on.");

		while (!hasGivenFirstItem || !hasGivenSecondItem || !hasGivenThirdItem) {
			System.out.println("\nAreas remaining:");
			if (!hasGivenFirstItem) {
				System.out.println("1. The weapon rack");
			}
			if (!hasGivenSecondItem) {
				System.out.println("2. The supply table");
			}
			if (!hasGivenThirdItem) {
				System.out.println("3. The storage corner");
			}
			System.out.print("Which area do you want to search? ");

			String choice = scanner.nextLine();
			processChoice(choice);
		}

		System.out.println("\nYou collected one item from every section.");
		System.out.println("You leave the barracks and continue to the next room.");
	}

	public void giveFirstItem() {
        if (hasGivenFirstItem) {
            System.out.println("You've already checked this area.");
            return;
        }
		System.out.println("You walk over to a collapsed weapon rack and sweep away the dust and broken wood.");
		System.out.println("You find three weapons:");
		System.out.println("1. A sword with a clean, sharp blade");
		System.out.println("2. An axe with a sturdy wooden handle");
		System.out.println("3. A bow with a strong, unbroken string");
		System.out.print("Which weapon do you take? ");
		String firstItem = scanner.nextLine();
        if (firstItem.equals("1")) {
            System.out.println("As you look closer at the sword, you notice that it is in perfect condition compared to the other items.");
            player.isSword = true;
        } else if (firstItem.equals("2")) {
            System.out.println("As you look closer at the axe, you notice that it is in perfect condition compared to the other items.");
            player.isAxe = true;
        } else if (firstItem.equals("3")) {
            System.out.println("As you look closer at the bow, you notice that it is in perfect condition compared to the other items.");
            player.isBow = true;
        } else {
            System.out.println("You didn't find anything useful.");
            return;
        }
        hasGivenFirstItem = true;
    }

    public void giveSecItem() {
        if (hasGivenSecondItem) {
            System.out.println("You've already checked this area.");
            return;
        }
    	System.out.println("You walk over to a dusty supply table covered in old containers.");
    	System.out.println("You find three supplies:");
    	System.out.println("1. A shield with only a few scratches");
    	System.out.println("2. A potion in a sealed glass bottle");
    	System.out.println("3. A clean bandage wrapped in cloth");
    	System.out.print("Which supply do you take? ");
    	String secondItem = scanner.nextLine();
        if (secondItem.equals("1")) {
            System.out.println("As you look closer at the shield, you notice that it is in perfect condition compared to the other items.");
            player.isShield = true;
        } else if (secondItem.equals("2")) {
            System.out.println("As you look closer at the potion, you notice that it is in perfect condition compared to the other items.");
            player.isPotion = true;
        } else if (secondItem.equals("3")) {
            System.out.println("As you look closer at the bandage, you notice that it is in perfect condition compared to the other items.");
            player.isBand = true;
        } else {
            System.out.println("You didn't find anything useful.");
            return;
        }
        hasGivenSecondItem = true;
	}

	public void giveThirdItem() {
		if (hasGivenThirdItem) {
            System.out.println("You've already checked this area.");
            return;
        }
		System.out.println("You search the dark storage corner behind a pile of broken crates.");
		System.out.println("You find three useful tools:");
		System.out.println("1. A sturdy grappling hook with a polished metal tip");
		System.out.println("2. A guard helmet with only a small dent");
		System.out.println("3. A torch with dry, usable wood");
		System.out.print("Which tool do you take? ");
		String thirdItem = scanner.nextLine();
        if (thirdItem.equals("1")) {
            System.out.println("As you look closer at the grappling hook, you notice that it is in perfect condition copared to the other items.");
            player.isHook = true;
        } else if (thirdItem.equals("2")) {
            System.out.println("As you look closer at the gaurd uniform, you notice that it is in perfect condition compared to the other items.");
            player.isGaurd = true;
        } else if (thirdItem.equals("3")) {
            System.out.println("As you look closer at the torch, you notice that it is in perfect condition compared to the other items.");
            player.isTorch = true;
        } else {
            System.out.println("You didn't find anything useful.");
            return;
        }
        hasGivenThirdItem = true;
	}

	public void processChoice(String choice) {
		if (choice.equals("1")) {
			giveFirstItem();
		} else if (choice.equals("2")) {
			giveSecItem();
		} else if (choice.equals("3")) {
			giveThirdItem();
		} else {
			System.out.println("That is not a valid area.");
		}
	}
}
