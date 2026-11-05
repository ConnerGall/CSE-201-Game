import java.util.Scanner;

public class Room5Dragon {

    private static final int DEAD = 0;

    private Scanner scanner;

    public Room5Dragon(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enter(player p) {
        System.out.println();
        System.out.println("--- Room 5: The Dragon's Chamber ---");
        System.out.println("The corridor opens into a vault of scorched stone.");
        System.out.println("A dragon lies across the only exit. One eye opens.");
        return processChoice(p);
    }

    public int processChoice(player p) {
        System.out.println();
        System.out.println("The dragon rises. What do you do?");
        System.out.println("1. Attack with your weapon");
        System.out.println("2. Use the supply you took from the barracks");
        System.out.println("3. Use the tool you took from the barracks");
        System.out.print("Choose 1, 2, or 3: ");

        String choice = scanner.nextLine().trim();

        System.out.println();

        if (choice.equals("1")) {
            useWeapon(p);
        } else if (choice.equals("2")) {
            useSupply(p);
        } else if (choice.equals("3")) {
            useTool(p);
        } else {
            System.out.println("You hesitate. The dragon does not.");
            p.health = DEAD;
        }

        showEnding(p);

        return p.health;
    }

    private void useWeapon(player p) {
        if (p.isSword || p.isAxe) {
            System.out.println("You drive the blade up beneath the jaw. The dragon");
            System.out.println("shudders once and goes still. The exit is clear.");
            return;
        }
        if (p.isBow) {
            System.out.println("The arrow glances off a scale the size of a shield.");
            System.out.println("The dragon has now noticed you properly.");
            p.health = DEAD;
            return;
        }
        System.out.println("You throw yourself at a dragon with your bare hands.");
        p.health = DEAD;
        return;
    }

    private void useSupply(player p) {
        if (p.isShield) {
            System.out.println("Fire breaks around the shield. You come out the far side");
            System.out.println("scorched but breathing, and you do not stop running.");
            injure(p);
            reportCondition(p);
            return;
        }
        if (p.isPotion) {
            System.out.println("You pull the stopper. The fumes catch before the liquid");
            System.out.println("reaches your lips, and the dragon has not even exhaled yet.");
            System.out.println("The potion was flammable.");
            p.health = DEAD;
            return;
        }
        if (p.isBand) {
            System.out.println("You unwrap a bandage at a dragon.");
            System.out.println("It does not help.");
            p.health = DEAD;
            return;
        }
        System.out.println("You reach for a supply you never picked up.");
        p.health = DEAD;
        return;
    }

    private void useTool(player p) {
        if (p.isHook) {
            System.out.println("You throw the hook over a beam and swing wide over the");
            System.out.println("dragon's back. You hit the far floor already running.");
            return;
        }
        if (p.isTorch) {
            System.out.println("You wave fire at something made of it.");
            p.health = DEAD;
            return;
        }
        if (p.isGaurd) {
            System.out.println("The helmet fooled skeletons. It does not fool this.");
            p.health = DEAD;
            return;
        }
        System.out.println("You reach for a tool you never picked up.");
        p.health = DEAD;
    }

    public void showEnding(player p) {
        System.out.println();
        if (p.health == DEAD) {
            System.out.println("=== YOU DIED ===");
            System.out.println("The dungeon keeps what it catches.");
        } else if (p.health == 1) {
            System.out.println("=== YOU ESCAPED ===");
            System.out.println("You step into open air bleeding, burned, and alive.");
        } else {
            System.out.println("=== YOU ESCAPED ===");
            System.out.println("You step into open air without a scratch on you.");
        }
    }

    private void injure(player p) {
        p.health = Math.max(DEAD, p.health - 1);
    }

    private void reportCondition(player p) {
        if (p.health == DEAD) {
            System.out.println("You were already hurt. The heat finishes it.");
        }
    }
}
