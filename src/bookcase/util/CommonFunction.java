package bookcase.util;

public class CommonFunction {
    public static void setMenuButton(String s, int menuButton) {
        System.out.print(s);
        menuButton = ScannerUtil.getInputInteger();
    }
}
