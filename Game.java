import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printTitle();

        boolean playing = true;
        while (playing) {
            resetPlayer();
            JailCell room1 = new JailCell(scanner);
            room1.enter();

            System.out.println();
            System.out.print("Play again? (yes/no): ");
            String answer = scanner.nextLine().trim();
            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                System.out.print("Please enter yes or no: ");
                answer = scanner.nextLine().trim();
            }
            playing = answer.equalsIgnoreCase("yes");
            System.out.println();
        }

        System.out.println("Thanks for playing Escape the Dungeon!");
    }

    private static void printTitle() {
        System.out.println("=========================================");
        System.out.println("           ESCAPE THE DUNGEON");
        System.out.println("=========================================");
        System.out.println("You have been thrown into a dungeon cell.");
        System.out.println("Make it through all five rooms to escape.");
        System.out.println();
        System.out.println("HOW TO PLAY");
        System.out.println("- Type the number of your choice and press Enter.");
        System.out.println("- The items you pick up in the barracks decide");
        System.out.println("  what you can do later. Read each room for clues.");
        System.out.println("- You can take one injury. A second one kills you.");
        System.out.println("=========================================");
        System.out.println();
    }

    private static void resetPlayer() {
        player.isSword = false;
        player.isAxe = false;
        player.isBow = false;
        player.isShield = false;
        player.isPotion = false;
        player.isBand = false;
        player.isHook = false;
        player.isGaurd = false;
        player.isTorch = false;
        player.health = 2;
    }

    public static void printStatus(player p) {
        String condition;
        if (p.health == 2) {
            condition = "Healthy";
        } else if (p.health == 1) {
            condition = "Injured";
        } else {
            condition = "Dead";
        }
        System.out.println("[ Health: " + condition + " | Items: " + itemList(p) + " ]");
    }

    private static String itemList(player p) {
        String items = "";
        if (p.isSword) items += "Sword, ";
        if (p.isAxe) items += "Axe, ";
        if (p.isBow) items += "Bow, ";
        if (p.isShield) items += "Shield, ";
        if (p.isPotion) items += "Potion, ";
        if (p.isBand) items += "Bandage, ";
        if (p.isHook) items += "Grappling hook, ";
        if (p.isGaurd) items += "Guard uniform, ";
        if (p.isTorch) items += "Torch, ";
        if (items.isEmpty()) {
            return "None";
        }
        return items.substring(0, items.length() - 2);
    }
}
