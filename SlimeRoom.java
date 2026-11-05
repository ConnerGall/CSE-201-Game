import java.util.Scanner;

public class SlimeRoom {

    private String introText = "You travel down into the next hallway where you find sicky goo on the walls. You keep following it until you stumble across a slime!";

    private Scanner scanner = new Scanner(System.in);

    public SlimeRoom(Scanner scanner){
        this.scanner = scanner;
    }

    public void enter(){
        System.out.println("--- Room 3: The Slime Room ---");
        System.out.println(introText);

        player player = new player();
        processChoice(player);
        

    }

    public int processChoice(player p){
        System.out.println();
        System.out.println("It will be difficult to get past the slimes. What do you do?");
        System.out.println("1. Attack");
        System.out.println("2. Sneak");

        String choice = scanner.nextLine();


        if(p.isShield == true || p.isTorch == true || p.isHook == true){
            System.out.println("The torch burns the slimes, the sheild blocks the slime, and the grappling hook lets you swing past. You're safe!");
            p.health = 2;
        }
        else if((p.isBow == true || p.isGaurd == true || p.isBand == true) && (p.isShield != true || p.isTorch != true || p.isHook != true)){
            System.out.println("The slime targets the guard uniform, arrows go right through the slime, and you cannot bandage yourself quick enough." + 
            " You do not have any qepons that are capable of overriding these weaknesses. You die.");
            p.health = 0;
        } else {
            System.out.println("You're able to make it past the slimes but not without being harmed first. You sustain injuries.");
            p.health = 1;
            }
        
        return p.health;


    }

}
