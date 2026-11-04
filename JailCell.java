import java.util.Scanner;

public class JailCell {

    public boolean hasNail = false;
    private Scanner scanner = new Scanner(System.in);
    private String introText = "You wake up in a jail cell. It is cold and you're not sure why you are here, but you know you need to get out. You get up and start to look around.";

    

    public void wakeUp(){
        System.out.println(introText);
    }

    public void examineBucket(){
        System.out.println("You look around to see a bucket in the cell. You take a peek to see what's in it but all it is small pool of water at the bottom.");
        System.out.println("You continue searching.");
    }

    public void examineBed(){
        System.out.println("First you look under the bed, nothing. Then you turn your attention to the straw mat used as a mattress. You pat down the top and find nothing. Then you pick up the mat and start " +
        "shaking it over the metal bed frame. After a couple of seconds you heard a clanging sound from something that dropped. It is an old rusty nail.");
        System.out.println("Do you pick up the nail? Yes or no");

        String response = scanner.nextLine();

        if(response.equalsIgnoreCase("yes")){
            System.out.println("you decide to pick up the rusty nail");
            hasNail = true;
        } else if(response.equalsIgnoreCase("no")){
            System.out.println("you do not pick up the old nail and continue on your search to get out");
        } else{
            System.out.println("Invalid response. Please enter yes or no");
        }
    }

    public void examineLock(){
        if(hasNail == true){
            System.out.println("With the nail in hand, you jam it into the lock. After a few minutes of trying to pry it open, it finally clicks and the door opens!");
        }else{
            System.out.println("You try everything in your power to get the lock to open, but it doesn't budge.");
            System.out.println("try another option");
        }
    }

    public int processChoice(){
        
        boolean doorOpen = false;

        while(doorOpen != true){
            System.out.println();
            System.out.println("What do you do?");
            System.out.println("1. Examine bucket");
            System.out.println("2. Examine straw bedding");
            System.out.println("3. Examine lock");

            String choice = scanner.nextLine();

            if(choice.equals("1")){
                examineBucket();
            } else if(choice.equals("2")){
                examineBed();
            } else if(choice.equals("3")){
                examineLock();
                if(hasNail == true){
                    doorOpen = true;
                }
            } else {
                System.out.println("Invalid response. Please enter again (1, 2, or 3)");
            }
        }

        return 1;
    }

}
