import java.awt.*;

public class Helper {
    //this method is to hold all of the helper methods for the game.
    public static Font getThemeFont(int size) {
        return new Font(ThemeConstants.fontTheme, Font.PLAIN, size);
    }
    public static String charToString(char[] chars) {
        String output = "";
        for (int i = 0; i < chars.length; i++) {
            output = output + chars[i];
        }
        return output;
    }
}