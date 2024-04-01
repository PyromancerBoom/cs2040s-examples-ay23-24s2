import java.util.Scanner;

public class flyingsafely {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int t = 0; t < testCases; t++) {
            int cities = scanner.nextInt();
            int pilots = scanner.nextInt();

            for (int p = 0; p < pilots; p++) {
                scanner.nextInt(); // read and ignore city 1
                scanner.nextInt(); // read and ignore city 2
            }

            // The answer is always the number of cities minus 1
            System.out.println(cities - 1);
        }

        scanner.close();
    }
}
