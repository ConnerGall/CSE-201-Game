import java.util.ArrayList;
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
        Game.printStatus(p);
        System.out.println();
        System.out.println("Four skeleton guards stand along a narrow stone corridor.");
        System.out.println("Their empty sockets turn toward you as you step through the door.");
        System.out.println("Old arrows lie scattered on the floor, having passed straight through their ribs.");
        System.out.println("The floor is covered in loose bones that crunch with every step.");
        System.out.println("The guards ignore anything that looks like one of their own.");
        return processChoice(p);
    }

    public int processChoice(player p) {
        ArrayList<String> actions = new ArrayList<String>();
        actions.add("weapon");
        if (p.isGaurd) {
            actions.add("uniform");
        }
        actions.add("sneak");

        System.out.println();
        System.out.println("What do you do?");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + describe(actions.get(i), p));
        }

        String action = actions.get(readChoice(actions.size()) - 1);
        System.out.println();

        if (action.equals("weapon")) {
            attackWithWeapon(p);
        } else if (action.equals("uniform")) {
            wearUniform(p);
        } else {
            System.out.println("You press against the wall and edge forward.");
            System.out.println("A bone cracks under your boot. They turn as one.");
            p.health = DEAD;
        }

        if (p.health == DEAD) {
            System.out.println();
            System.out.println("=== YOU DIED ===");
            System.out.println("The skeleton guards were the end of your run.");
            return p.health;
        }

        Room5Dragon room5 = new Room5Dragon(scanner);
        room5.enter(p);

        return p.health;
    }

    private String describe(String action, player p) {
        if (action.equals("weapon")) {
            return "Attack with your " + weaponName(p);
        } else if (action.equals("uniform")) {
            return "Put on the guard uniform and walk past";
        }
        return "Try to sneak along the wall";
    }

    private int readChoice(int max) {
        while (true) {
            System.out.print("Choose 1-" + max + ": ");
            String input = scanner.nextLine().trim();
            for (int i = 1; i <= max; i++) {
                if (input.equals(String.valueOf(i))) {
                    return i;
                }
            }
            System.out.println("That's not an option. Try again.");
        }
    }

    private String weaponName(player p) {
        if (p.isSword) return "sword";
        if (p.isAxe) return "axe";
        if (p.isBow) return "bow";
        return "bare hands";
    }

    private void attackWithWeapon(player p) {
        if (p.isSword || p.isAxe) {
            System.out.println("Steel meets bone. You put all four down, but a rusted");
            System.out.println("blade catches your side on the way through.");
            injure(p);
            reportCondition(p);
            return;
        }
        if (p.isBow) {
            System.out.println("You loose an arrow. It passes clean between two ribs");
            System.out.println("and clatters off the wall behind. They keep coming.");
            p.health = DEAD;
            return;
        }
        System.out.println("You swing at them barehanded.");
        System.out.println("It goes exactly as well as you would expect.");
        p.health = DEAD;
    }

    private void wearUniform(player p) {
        System.out.println("You pull on the torn guard uniform and walk at an");
        System.out.println("unhurried pace. The skeletons do not so much as turn.");
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
