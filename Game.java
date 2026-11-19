import java.util.Scanner;

// Starts the game, shows the title screen, and handles "play again"
public class Game {
    public static void main(String[] args) {
        // One Scanner shared by every room
        Scanner scanner = new Scanner(System.in);
        printTitle();

        String again = "yes";
        while (again.equals("yes")) {
            resetPlayer();

            // Room 1 calls Room 2, Room 2 calls Room 3, and so on
            new JailCell(scanner).enter();

            // Keep asking until the player types yes or no
            do {
                System.out.print("\nPlay again? (yes/no): ");
                again = scanner.nextLine().trim().toLowerCase();
            } while (!again.equals("yes") && !again.equals("no"));
        }
        System.out.println("Thanks for playing Escape the Dungeon!");
    }

    // Title, goal, and how to play
    private static void printTitle() {
        System.out.println("=========================================");
        System.out.println("           ESCAPE THE DUNGEON");
        System.out.println("=========================================");
        System.out.println("Make it through all five rooms to escape.");
        System.out.println("- Type the number of your choice and press Enter.");
        System.out.println("- Your barracks items decide what you can do later.");
        System.out.println("- Read each room for clues.");
        System.out.println("- You can take one injury. A second one kills you.");
        System.out.println("=========================================\n");
    }

    // Player fields are static, so clear them before each new run
    private static void resetPlayer() {
        player.isSword = player.isAxe = player.isBow = false;
        player.isShield = player.isPotion = player.isBand = false;
        player.isHook = player.isGaurd = player.isTorch = false;
        player.health = 2;
    }

    // Prints health and items, e.g. [ Health: Injured | Items: Axe Shield Torch ]
    public static void printStatus(player p) {
        String[] condition = {"Dead", "Injured", "Healthy"};
        String items = "";
        if (p.isSword) items += "Sword ";
        if (p.isAxe) items += "Axe ";
        if (p.isBow) items += "Bow ";
        if (p.isShield) items += "Shield ";
        if (p.isPotion) items += "Potion ";
        if (p.isBand) items += "Bandage ";
        if (p.isHook) items += "Hook ";
        if (p.isGaurd) items += "Uniform ";
        if (p.isTorch) items += "Torch ";
        System.out.println("[ Health: " + condition[p.health] + " | Items: " + items.trim() + " ]");
    }
}
