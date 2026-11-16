import java.util.Scanner;

public class SlimeRoom {

    private String introText = "You travel down into the next hallway where you find sicky goo on the walls. You keep following it until you stumble across a slime!";

    private Scanner scanner;

    public SlimeRoom(Scanner scanner){
        this.scanner = scanner;
    }

    public int enter(player p){
        System.out.println("--- Room 3: The Slime Room ---");
        System.out.println(introText);
        System.out.println();
        return processChoice(p);
        

    }

    public int processChoice(player p){
        System.out.println();
        System.out.println("It will be difficult to get past the slimes. What do you do?");
        System.out.println("1. Attack");
        System.out.println("2. Sneak");
        System.out.println();

        String choice = scanner.nextLine();

        while(!choice.equals("1") && !choice.equals("2")){
            System.out.println("Please enter 1 (attack) or 2 (sneak)");
            choice = scanner.nextLine();
        }

        if(choice.equals("1")){
            attackSlimes(p);
        } else {
            sneakPastSlimes(p);
        }

        if(p.health == 0){
            System.out.println();
            System.out.println("--- YOU DIED ---");
            System.out.println("The slimes were the end of your run.");
            return p.health;
        }

        Room4SkeletonGuards room4 = new Room4SkeletonGuards(scanner);
        room4.enter(p);
        return p.health;

    }

    private void attackSlimes(player p){
        boolean failTool = p.isBow || p.isGaurd || p.isBand;

        if(p.isTorch){
            System.out.println("You fire the torches nto the slimes. This hiss and slime away. You're safe!");
            p.health = 2;
        } else if(p.isShield){
            System.out.println("You shield yourself and bash through the slimes. You make it to the other side of the room fully entact and fully healthy!");
            p.health = 2;
        } else if (failTool){
            System.out.println("The equipment you've choosen leads to your demise. The slimes get you and you die!");
            p.health = 0;
        } else {
            System.out.println("Lacking the proper equipment, you fight bare-handed. You're able to make it through but not before being serverly injured.");
            p.health = 1;
        }
    }

    private void sneakPastSlimes(player p){
        if(p.isHook){
            System.out.println("You fire the grappling hook and swing silently over the slimes. You're safe!");
            p.health = 2;
        } else if(p.isGaurd){
            System.out.println("The slimes are drawn to the guard uniform you are wearing. They surround you and before you know it you are dead.");
            p.health = 0;
        } else {
            System.out.println("You creep past the slimes but accidently brush into one. You book it past them but not before they are able to do some damage. You sustain injuries");
            p.health = 1;
        }
    }

}
