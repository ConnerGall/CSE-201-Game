import java.util.Scanner;

/**
Temporary driver for testing Rooms 4 and 5 on their own.

PLACEHOLDER: this stands in for the real Game class until the team
builds it. It sets up the player the way Rooms 1 through 3 would have,
then runs Room 4 into Room 5.
*/
public class RoomTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        player p = new player();
        // player() leaves health at 0, so set it here until the team
        // adds a constructor to the player class
        p.health = 100;

        // Items the player would be carrying out of Rooms 1-3
        p.isSword = true;
        p.isGaurd = true;
        p.isShield = true;
        p.isPotion = true;

        Room4SkeletonGuards room4 = new Room4SkeletonGuards(scanner);
        Room5Dragon room5 = new Room5Dragon(scanner);

        int result = room4.enter(p);

        if (result == 0) {
            System.out.println();
            System.out.println("=== YOU DIED ===");
            System.out.println("The skeleton guards were the end of your run.");
            scanner.close();
            return;
        }

        room5.enter(p);
        room5.showEnding(p);
        scanner.close();
    }
}
