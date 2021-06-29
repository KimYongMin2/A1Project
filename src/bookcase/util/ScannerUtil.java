package bookcase.util;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);
    public static String getInputString(){
        return scanner.nextLine();
    }

    public static int getInputInteger(){
        return Integer.parseInt(scanner.nextLine());
    }

    public static double getInputDouble(){
        return Double.parseDouble(scanner.nextLine());
    }

    public static char getInputChar(){
        return scanner.nextLine().charAt(0);
    }

    public static String getInputStringS(String s){
        System.out.print(s);
        return scanner.nextLine();
    }

    public static int getInputIntegerS(String s){
        System.out.print(s);
        return Integer.parseInt(scanner.nextLine());
    }

    public static double getInputDoubleS(String s){
        System.out.print(s);
        return Double.parseDouble(scanner.nextLine());
    }

    public static char getInputCharS(String s){
        System.out.print(s);
        return scanner.nextLine().charAt(0);
    }
}
