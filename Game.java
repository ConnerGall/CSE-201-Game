import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JailCell room1 = new JailCell(scanner);
        room1.enter();
    }
}
