import java.util.Scanner;

public class Room1and3Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        player p1 = new player();
        p1.isShield = true;
        p1.isPotion = true;
        p1.isHook = true;

        JailCell room1 = new JailCell(scanner);

        SlimeRoom room3 = new SlimeRoom(scanner);

        room1.processChoice();

        room3.enter();
        int result = room3.processChoice(p1);

        if(result == 0){
            System.out.println("You died.");
        } else if(result == 1){
            System.out.println("You made it through the slime room but were injured");
        } else{
            System.out.println("You made it through the Slime Room unharmed");
        }

        scanner.close();

        
    }

}
