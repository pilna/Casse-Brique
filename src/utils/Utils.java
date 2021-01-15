package utils;

import java.util.Scanner;

public abstract class Utils {
    public static Integer valueBetween(Integer lowerBorn, Integer higherBorn){
        if (lowerBorn > higherBorn){
            Integer temp = lowerBorn;
            lowerBorn = higherBorn;
            higherBorn = temp;
        }

        while (true) {
            Integer input;
            Scanner sc = new Scanner(System.in);

            if (!(sc.hasNextInt())){
                System.out.println(String.format("Select number between %d and %d", lowerBorn, higherBorn));
                continue;
            }

            input = sc.nextInt();

            if (input < lowerBorn || input > higherBorn) {
                System.out.println(String.format("Select number between %d and %d", lowerBorn, higherBorn));
                System.out.println(String.format("Value enter: %d", input));
                continue;
            }

            return input;
        }
    }
}
