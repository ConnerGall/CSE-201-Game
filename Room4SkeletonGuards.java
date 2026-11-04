import java.util.Scanner;

/**
Room 4 of the dungeon: a corridor patrolled by skeleton guards.

The player chooses one carried item to use. Wearing the guard uniform
gets the player past untouched. Fighting with the sword works but costs
health. Anything else gets the player killed.
*/
public class Room4SkeletonGuards {

    /** Room number the player advances to on success. */
    private static final int NEXT_ROOM = 5;

    /** Value returned when the player dies in this room. */
    private static final int DEATH = 0;

    /** Health lost when fighting the skeletons with the sword. */
    private static final int SWORD_DAMAGE = 30;

    private Scanner scanner;

    /**
    Constructor for Room4SkeletonGuards.
    @param scanner the shared input scanner
    */
    public Room4SkeletonGuards(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
    Prints the room description and the available choices, then reads
    and resolves the player's choice.
    @param p the player entering the room
    @return the next room number, or DEATH if the player died
    */
    public int enter(player p) {
        System.out.println();
        System.out.println("--- Room 4: The Guard Corridor ---");
        System.out.println("Four skeleton guards stand along a narrow stone corridor.");
        System.out.println("Their empty sockets turn toward you as you step through the door.");
        System.out.println();
        System.out.println("What do you do?");
        System.out.println("  1. Wear the guard uniform and walk past");
        System.out.println("  2. Attack them with the sword");
        System.out.println("  3. Raise the shield and push through");
        System.out.println("  4. Run for the far door");
        System.out.print("Choose 1-4: ");

        int choice = readChoice(4);
        return processChoice(choice, p);
    }

    /**
    Applies the result of the player's choice.
    @param choice the option the player selected
    @param p the player in the room
    @return the next room number, or DEATH if the player died
    */
    public int processChoice(int choice, player p) {
        System.out.println();

        if (choice == 1) {
            if (p.isGaurd) {
                System.out.println("You pull the uniform over your shoulders and walk at an");
                System.out.println("unhurried pace. The skeletons do not so much as turn.");
                return NEXT_ROOM;
            }
            System.out.println("You reach for a uniform you never picked up.");
            System.out.println("The nearest skeleton runs you through.");
            p.health = 0;
            return DEATH;
        }

        if (choice == 2) {
            if (p.isSword) {
                p.health = Math.max(0, p.health - SWORD_DAMAGE);
                if (p.checkHealth() == 0) {
                    System.out.println("You put all four down and bleed out in the corridor.");
                    return DEATH;
                }
                System.out.println("Steel meets bone. You put all four down, but a blade");
                System.out.println("catches your side on the way through.");
                System.out.println("You lose " + SWORD_DAMAGE + " health. Health: "
                    + p.checkHealth());
                return NEXT_ROOM;
            }
            System.out.println("You swing at them barehanded.");
            System.out.println("It goes exactly as well as you would expect.");
            p.health = 0;
            return DEATH;
        }

        if (choice == 3) {
            System.out.println("The shield turns the first two strikes.");
            System.out.println("The other two guards simply walk around you.");
            p.health = 0;
            return DEATH;
        }

        System.out.println("You run. Bone hands catch you before the door.");
        p.health = 0;
        return DEATH;
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
