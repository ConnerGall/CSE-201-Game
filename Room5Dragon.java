import java.util.Scanner;

/**
Room 5 of the dungeon: the dragon's chamber and the final encounter.

The sword is the only way to kill the dragon. The shield lets the player
survive at a cost. The potion is flammable and kills the player who tries
it. Anything else ends the run.
*/
public class Room5Dragon {

    /** Value returned when the player escapes the dungeon alive. */
    private static final int ESCAPED = -1;

    /** Value returned when the player dies in this room. */
    private static final int DEATH = 0;

    /** Health lost when surviving the dragon's fire behind the shield. */
    private static final int FIRE_DAMAGE = 40;

    private Scanner scanner;

    /**
    Constructor for Room5Dragon.
    @param scanner the shared input scanner
    */
    public Room5Dragon(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
    Prints the room description and the available choices, then reads
    and resolves the player's choice.
    @param p the player entering the room
    @return ESCAPED if the player survives, or DEATH if the player died
    */
    public int enter(player p) {
        System.out.println();
        System.out.println("--- Room 5: The Dragon's Chamber ---");
        System.out.println("The corridor opens into a vault of scorched stone.");
        System.out.println("A dragon lies across the only exit. One eye opens.");
        System.out.println();
        System.out.println("What do you do?");
        System.out.println("  1. Attack with the sword");
        System.out.println("  2. Take cover behind the shield");
        System.out.println("  3. Drink the potion");
        System.out.println("  4. Try to sneak past in the guard uniform");
        System.out.print("Choose 1-4: ");

        int choice = readChoice(4);
        return processChoice(choice, p);
    }

    /**
    Applies the result of the player's choice.
    @param choice the option the player selected
    @param p the player in the room
    @return ESCAPED if the player survives, or DEATH if the player died
    */
    public int processChoice(int choice, player p) {
        System.out.println();

        if (choice == 1) {
            if (p.isSword) {
                System.out.println("You drive the sword up beneath the jaw. The dragon");
                System.out.println("shudders once and goes still. The exit is clear.");
                return ESCAPED;
            }
            System.out.println("You throw yourself at a dragon with your bare hands.");
            p.health = 0;
            return DEATH;
        }

        if (choice == 2) {
            if (p.isShield) {
                p.health = Math.max(0, p.health - FIRE_DAMAGE);
                if (p.checkHealth() == 0) {
                    System.out.println("The shield holds, but you do not. The heat takes you.");
                    return DEATH;
                }
                System.out.println("Fire breaks around the shield. You come out the far side");
                System.out.println("scorched but breathing, and you do not stop running.");
                System.out.println("You lose " + FIRE_DAMAGE + " health. Health: "
                    + p.checkHealth());
                return ESCAPED;
            }
            System.out.println("You crouch behind an arm you are not carrying a shield on.");
            p.health = 0;
            return DEATH;
        }

        if (choice == 3) {
            if (p.isPotion) {
                System.out.println("You pull the stopper. The fumes catch before the liquid");
                System.out.println("reaches your lips, and the dragon has not even exhaled yet.");
                System.out.println("The potion was flammable.");
            } else {
                System.out.println("You reach for a potion you never found.");
            }
            p.health = 0;
            return DEATH;
        }

        System.out.println("The uniform fooled skeletons. It does not fool this.");
        p.health = 0;
        return DEATH;
    }

    /**
    Prints the closing text for the run.
    @param p the player whose run has ended
    */
    public void showEnding(player p) {
        System.out.println();
        if (p.checkHealth() > 0) {
            System.out.println("=== YOU ESCAPED ===");
            System.out.println("You step into open air with " + p.checkHealth()
                + " health remaining.");
        } else {
            System.out.println("=== YOU DIED ===");
            System.out.println("The dungeon keeps what it catches.");
        }
    }

    /**
    Reads a menu selection, re-prompting until the input is a number
    between 1 and max.
    @param max the highest valid option
    @return the number the player chose
    */
    private int readChoice(int max) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= 1 && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // fall through to the re-prompt below
            }
            System.out.print("Enter a number from 1 to " + max + ": ");
        }
    }
}
