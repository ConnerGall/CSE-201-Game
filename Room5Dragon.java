import java.util.Scanner;

// Room 5: the dragon (final room)
// Sword/axe/hook = escape, shield = escape injured, everything else = dead
public class Room5Dragon {
    private Scanner scanner;

    public Room5Dragon(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enter(player p) {
        System.out.println("\n--- Room 5: The Dragon's Chamber ---");
        Game.printStatus(p);
        System.out.println("A dragon lies across the only exit. One eye opens.");
        System.out.println("Snapped arrows lie beside its armored scales.");          // bow fails
        System.out.println("The air ripples with heat. Anything that burns won't last."); // potion/torch fail
        System.out.println("Thick wooden beams cross the ceiling toward the exit.");  // hook works
        return processChoice(p);
    }

    public int processChoice(player p) {
        System.out.println("\nThe dragon rises. What do you do?");
        System.out.println("1. Attack with your " + weaponName(p));
        System.out.println("2. Use your " + supplyName(p));
        System.out.println("3. Use your " + toolName(p));

        int choice = readChoice();

        // Sword or axe: kill the dragon, no damage
        if (choice == 1 && (p.isSword || p.isAxe)) {
            System.out.println("You drive the blade up beneath its jaw. The exit is clear.");
        // Shield: survive the fire but lose 1 health (dies if already injured)
        } else if (choice == 2 && p.isShield) {
            System.out.println("Fire breaks around your shield. You make it out scorched.");
            p.health--;
            if (p.health == 0) System.out.println("You were already hurt. The heat finishes you.");
        // Grappling hook: swing over the dragon, no damage
        } else if (choice == 3 && p.isHook) {
            System.out.println("You swing from a beam over the dragon's back to the exit.");
        // Potion: it's flammable, so the player dies
        } else if (choice == 2 && p.isPotion) {
            System.out.println("The heat ignites the potion. It was flammable.");
            p.health = 0;
        // Anything else (bow, bandage, torch, uniform): the player dies
        } else {
            System.out.println("It does nothing against a dragon. The dragon does plenty.");
            p.health = 0;
        }

        showEnding(p);
        return p.health;
    }

    // Keeps asking until the player types 1, 2, or 3, so typos don't count as a choice.
    // while (true) only ends when the return runs with a valid number.
    private int readChoice() {
        while (true) {
            System.out.print("Choose 1-3: ");
            String input = scanner.nextLine().trim();
            if (input.matches("[1-3]")) return Integer.parseInt(input);
            System.out.println("That's not an option. Try again.");
        }
    }

    private String weaponName(player p) {
        if (p.isSword) return "sword";
        if (p.isAxe) return "axe";
        return "bow";
    }

    private String supplyName(player p) {
        if (p.isShield) return "shield";
        if (p.isPotion) return "potion";
        return "bandage";
    }

    private String toolName(player p) {
        if (p.isHook) return "grappling hook";
        if (p.isGaurd) return "guard uniform";
        return "torch";
    }

    // 0 = died, 1 = escaped injured, 2 = escaped unharmed
    public void showEnding(player p) {
        if (p.health == 0) {
            System.out.println("\n=== YOU DIED ===");
            System.out.println("The dungeon keeps what it catches.");
        } else if (p.health == 1) {
            System.out.println("\n=== YOU ESCAPED ===");
            System.out.println("You step into open air bleeding, burned, and alive.");
        } else {
            System.out.println("\n=== YOU ESCAPED ===");
            System.out.println("You step into open air without a scratch on you.");
        }
    }
}
