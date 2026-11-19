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
        Game.printStatus(p);
        System.out.println();
        System.out.println("The corridor opens into a vault of scorched stone.");
        System.out.println("A dragon lies across the only exit. One eye opens.");
        System.out.println("Snapped arrows lie in a heap beside its armored scales.");
        System.out.println("The air ripples with heat. Anything that burns would not last long in here.");
        System.out.println("High above, thick wooden beams cross the ceiling toward the exit.");
        return processChoice(p);
    }

    public int processChoice(player p) {
        System.out.println();
        System.out.println("The dragon rises. What do you do?");
        System.out.println("1. Attack with your " + weaponName(p));
        System.out.println("2. Use your " + supplyName(p));
        System.out.println("3. Use your " + toolName(p));

        int choice = readChoice(3);
        System.out.println();

        if (choice == 1) {
            useWeapon(p);
        } else if (choice == 2) {
            useSupply(p);
        } else {
            useTool(p);
        }

        showEnding(p);

        return p.health;
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

    private String supplyName(player p) {
        if (p.isShield) return "shield";
        if (p.isPotion) return "potion";
        if (p.isBand) return "bandage";
        return "empty pockets";
    }

    private String toolName(player p) {
        if (p.isHook) return "grappling hook";
        if (p.isGaurd) return "guard uniform";
        if (p.isTorch) return "torch";
        return "empty hands";
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
            System.out.println("reaches your lips. The potion was flammable.");
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
            System.out.println("The uniform fooled skeletons. It does not fool this.");
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
