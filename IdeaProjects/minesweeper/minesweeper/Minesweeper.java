package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.consoleui.UserInterface;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
    private static long startMillis;
    private static BestTimes bestTimes= new BestTimes();
    private static Minesweeper instance;
 
    /**
     * Constructor.
     */

    private Minesweeper() {
        instance = this;
        userInterface = new ConsoleUI();
        startMillis = System.currentTimeMillis();

        Field field = new Field(9, 9, 10);
        userInterface.newGameStarted(field);
    }

    public static int getPlayingSeconds(){
        return (int)(System.currentTimeMillis() - startMillis)/1000;
    }
    public static Minesweeper getInstance(){
        return instance;
    }


    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
    }

    public static BestTimes getBestTimes() {
        return bestTimes;
    }
}
