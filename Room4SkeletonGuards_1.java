import java.util.Scanner;

public class Room4SkeletonGuards {

    private static final int DEAD = 0;

    private Scanner scanner;

    public Room4SkeletonGuards(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enter(player p) {
        System.out.println();
        System.out.println("--- Room 4: The Guard Corridor ---");
        System.out.println("Four skeleton guards stand along a narrow stone corridor.");
        System.out.println("Their empty sockets turn toward you as you step through the door.");
        return processChoice(p);
    }

    public int processChoice(player p) {
        System.out.println();
        System.out.println("What do you do?");
        System.out.println("1. Attack with your weapon");
        System.out.println("2. Put on the guard helmet and walk past");
        System.out.println("3. Try to sneak along the wall");
        System.out.print("Choose 1, 2, or 3: ");

        String choice = scanner.nextLine().trim();

        System.out.println();

        if (choice.equals("1")) {
            return attackWithWeapon(p);
        } else if (choice.equals("2")) {
            return wearHelmet(p);
        } else if (choice.equals("3")) {
            System.out.println("You press against the wall and edge forward.");
            System.out.println("Bone does not need eyes to hear you. They turn as one.");
            p.health = DEAD;
            return p.health;
        } else {
            System.out.println("You freeze, unable to decide.");
            System.out.println("The skeletons do not have that problem.");
            p.health = DEAD;
            return p.health;
        }
    }

    private int attackWithWeapon(player p) {
        if (p.isSword || p.isAxe) {
            System.out.println("Steel meets bone. You put all four down, but a rusted");
            System.out.println("blade catches your side on the way through.");
            injure(p);
            reportCondition(p);
            return p.health;
        }
        if (p.isBow) {
            System.out.println("You loose an arrow. It passes clean between two ribs");
            System.out.println("and clatters off the wall behind. They keep coming.");
            p.health = DEAD;
            return p.health;
        }
        System.out.println("You swing at them barehanded.");
        System.out.println("It goes exactly as well as you would expect.");
        p.health = DEAD;
        return p.health;
    }

    private int wearHelmet(player p) {
        if (p.isGaurd) {
            System.out.println("You settle the helmet over your head and walk at an");
            System.out.println("unhurried pace. The skeletons do not so much as turn.");
            return p.health;
        }
        System.out.println("You reach for a helmet you never picked up.");
        System.out.println("The nearest skeleton runs you through.");
        p.health = DEAD;
        return p.health;
    }

    private void injure(player p) {
        p.health = Math.max(DEAD, p.health - 1);
    }

    private void reportCondition(player p) {
        if (p.health == DEAD) {
            System.out.println("You were already hurt. This one finishes you.");
        } else {
            System.out.println("You are injured, but you are through.");
        }
    }
}
