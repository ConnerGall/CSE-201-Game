import java.util.Scanner;

// Room 4: get past the skeleton guards
// Sword/axe = injured, uniform = safe, bow/sneak = dead
public class Room4SkeletonGuards {
    private Scanner scanner;

    public Room4SkeletonGuards(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enter(player p) {
        System.out.println("\n--- Room 4: The Guard Corridor ---");
        Game.printStatus(p);
        System.out.println("Four skeleton guards stand along a narrow stone corridor.");
        System.out.println("Old arrows lie on the floor, having passed straight through their ribs."); // bow fails
        System.out.println("Loose bones crunch with every step.");                                   // sneaking fails
        System.out.println("The guards ignore anything that looks like one of their own.");         // uniform works
        return processChoice(p);
    }

    public int processChoice(player p) {
        // The uniform option only shows if the player has it
        System.out.println("\nWhat do you do?");
        System.out.println("1. Attack with your " + weaponName(p));
        if (p.isGaurd) {
            System.out.println("2. Put on the guard uniform and walk past");
            System.out.println("3. Sneak along the wall");
        } else {
            System.out.println("2. Sneak along the wall");
        }

        // Menu has 3 options with the uniform and 2 without, so readChoice gets 3 or 2.
        // Without the uniform, sneak is listed as 2, so change it to 3 to match the code below.
        int choice = readChoice(p.isGaurd ? 3 : 2);
        if (!p.isGaurd && choice == 2) choice = 3;

        if (choice == 1 && (p.isSword || p.isAxe)) {
            System.out.println("You cut down all four, but a rusted blade catches your side.");
            p.health--;
            if (p.health == 0) System.out.println("You were already hurt. This one finishes you.");
        } else if (choice == 1) {
            System.out.println("Your arrow passes clean through their ribs. They keep coming.");
            p.health = 0;
        } else if (choice == 2) {
            System.out.println("In the guard uniform, you walk right past. They don't even turn.");
        } else {
            System.out.println("A bone cracks under your boot. They turn as one.");
            p.health = 0;
        }

        if (p.health == 0) {
            System.out.println("\n=== YOU DIED ===");
            System.out.println("The skeleton guards were the end of your run.");
            return 0;
        }

        // Survived, so move on to the dragon
        return new Room5Dragon(scanner).enter(p);
    }

    // Keeps asking until the player types a single number from 1 to max, so typos don't count as a choice.
    // while (true) only ends when the return runs with a valid number.
    private int readChoice(int max) {
        while (true) {
            System.out.print("Choose 1-" + max + ": ");
            String input = scanner.nextLine().trim();
            if (input.matches("[1-" + max + "]")) return Integer.parseInt(input);
            System.out.println("That's not an option. Try again.");
        }
    }

    private String weaponName(player p) {
        if (p.isSword) return "sword";
        if (p.isAxe) return "axe";
        return "bow";
    }
}
