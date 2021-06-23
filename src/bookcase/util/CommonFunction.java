package bookcase.util;

public class CommonFunction {
    public static int setMenuButton(String s, int menuButton) {
        System.out.print(s);
        menuButton = ScannerUtil.getInputInteger();
        return menuButton;
    }
}
