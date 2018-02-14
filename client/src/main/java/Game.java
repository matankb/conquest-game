import javax.swing.*;

public class Game {
    private JPanel panel;
    private TextManager textManager;

    /*
    Game class handles the initial and new gameStates as they arrive, and
    turns the data into graphics.
     */

    public Game() {
        GameManager.replaceContentPane(panel);
    }
    public static void beginGame() {
        //activates ActionListeners and removes text saying waiting for players
    }
}
