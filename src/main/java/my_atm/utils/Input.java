package my_atm.utils;

import java.util.Scanner;

public class Input {
    private static Scanner input = new Scanner(System.in);

    public static String readString(){
        return input.nextLine();
    }

    public static int readInt(){
        return input.nextInt();
    }

    public static double readDouble(){
        return input.nextDouble();
    }
}
